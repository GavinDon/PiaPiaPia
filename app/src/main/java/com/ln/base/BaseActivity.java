package com.ln.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ln.pia.R;
import com.ln.utils.ActivityCollector;
import com.ln.utils.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by linan   on 2016/12/29.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private static PermissionListener mListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(this.setLayout());
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        //设置透明状态栏
//        new ZTLUtils(this).setTranslucentStatus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// 隐藏软键盘
        initialize();
    }

    /**
     * @return layoutResID
     */
    public abstract int setLayout();

    public void initView() {
        addToolbar();
    }


    public abstract void setupData();

    /**
     * 程序入口
     */
    public void initialize() {
        initView();
        setupData();
    }

    /**
     * 初始化toolbar
     */
    public void addToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mTitle = (TextView) findViewById(R.id.title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);//默认设置为true显示返回按扭
    }

    public Toolbar getToolbar(String title) {
        mTitle.setText(title);
        return mToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        ActivityCollector.removeActivity(this);
    }

    /**
     * 打开Activity并默认不关闭当前Activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false);
    }

    /**
     * 打开Activity并不需要传值
     */
    public void gotoActivity(Class<?> clz, boolean isClose) {
        gotoActivity(clz, isClose, null);

    }

    /**
     * 打开Activity并传值跳转成功关闭当前Activity
     *
     * @param clz     需要跳转到activity
     * @param isClose 是否需要关闭当前Activity
     * @param bundle  传递bundle数据
     */
    public void gotoActivity(Class<?> clz, boolean isClose, Bundle bundle) {
        Intent mIntent = new Intent(this, clz);
        if (bundle != null) mIntent.putExtras(bundle);
        startActivity(mIntent);
        if (isClose) {
            this.finish();
        }
    }

    public static void requestRuntimePermission(String[] permissions, PermissionListener listener) {
            Activity topActivity = ActivityCollector.getTopActivity();
            if (topActivity == null) {
                return;
            }
            mListener = listener;
            List<String> permissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }
            if (!permissionList.isEmpty()) {
                //把未授权的加入到List中
                ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), 1);
            } else {
                //表示所有都已经授权
                mListener.onGranted();
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermission = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermission.add(permission);
                        }
                    }
                    if (deniedPermission.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        mListener.onDenied(deniedPermission);
                    }
                }
            default:
                break;
        }

    }

}
