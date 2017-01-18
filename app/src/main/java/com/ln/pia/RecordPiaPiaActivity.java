package com.ln.pia;

import android.Manifest;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.ln.baidu.LocationMap;
import com.ln.base.BaseActivity;
import com.ln.db.DbCore;
import com.ln.db.RecordPiaDB;
import com.ln.db.RecordPiaDBDao;
import com.ln.utils.MTools;
import com.ln.utils.PermissionListener;
import com.ln.views.SweetDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class RecordPiaPiaActivity extends BaseActivity implements LocationMap.onRecordLocation {
    @Bind(R.id.bmap)
    MapView bMap;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private String subLocation;//分割后的地址
    private RecordPiaDBDao dao; //记录数据库
    private SweetDialog mDialog;

    @Override
    public int setLayout() {
        return R.layout.activity_record_pia_pia;
    }

    @Override
    public void initView() {
        super.initView();
        getToolbar("地图标记");
        if (Build.VERSION.SDK_INT >= 23) {
            requestRuntimePermission(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION
                    , Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
            }, new PermissionListener() {
                @Override
                public void onGranted() {
                    new LocationMap(RecordPiaPiaActivity.this, bMap);
                }

                @Override
                public void onDenied(List<String> deniedPermission) {
                    for (String permission : deniedPermission) {
                        Toast.makeText(RecordPiaPiaActivity.this, "被拒绝的权限：" + permission, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            new LocationMap(this, bMap);
        }
    }

    @Override
    public void setupData() {

    }

    @OnClick(R.id.fab)
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                floatingAction();
                break;
        }
    }

    @Override
    protected void onResume() {
        bMap.onResume();
        super.onResume();
    }

    @Override
    public void onRecord(String location) {
        try {
            subLocation = location.split("国")[1];
            getToolbar(subLocation);//设置到标题
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();


        }

    }

    /***
     * 展示对话框确认是否成功
     */
    private void floatingAction() {
        mDialog = new SweetDialog(this);
        mDialog.setContent("已经啪啪完了嘛？")
                .setLeftImage(R.mipmap.left)
                .setOnSweetListener(new SweetDialog.OnSweetListener() {

                    @Override
                    public void onSweetConfirm(boolean yufang, boolean doubleCk) {
                        insertRecord(yufang, doubleCk);
                        mDialog.dismiss();
                    }

                    @Override
                    public void onSweetCancle() {
                        Toast.makeText(RecordPiaPiaActivity.this, "没有啪啪？", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    }
                });

    }

    /**
     * 确认打卡 插入数据到数据库
     */
    private void insertRecord(boolean yufang, boolean doubleCk) {
        if (TextUtils.isEmpty(subLocation)) {
            Toast.makeText(this, "稍后再尝试", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(RecordPiaPiaActivity.this, "上车喽", Toast.LENGTH_SHORT).show();
            dao = DbCore.getDaoSession().getRecordPiaDBDao();
            RecordPiaDB rdb = new RecordPiaDB();
            rdb.setDate(MTools.getDate());
            rdb.setTime(MTools.getDate("HH:mm:ss"));
            rdb.setLocation(subLocation);
            rdb.setDoublec(doubleCk);
            rdb.setYuFang(yufang);
            dao.insertInTx(rdb);
        }
    }

    @Override
    protected void onDestroy() {
        bMap.onDestroy();
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
