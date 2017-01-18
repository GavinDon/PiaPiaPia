package com.ln.pia;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ln.adapter.MainRecyclerViewAdapter;
import com.ln.base.BaseActivity;
import com.ln.utils.DividerItemDecoration;
import com.ln.utils.PermissionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainRecyclerViewAdapter.OnClickListener {
    @Bind(R.id.main_rv)
    RecyclerView mainRv;
    private MainRecyclerViewAdapter mAdapter;
    private List<Map<String, String>> lst;
    private Intent callIntent;//播打电话Intent;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        getToolbar("啪啪");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        initRecycleView();
    }

    private void initRecycleView() {
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mainRv.setHasFixedSize(true);
        mainRv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mainRv.setLayoutManager(lm);
        String textArray[] = getResources().getStringArray(R.array.main_list_text);
        String picArray[] = getResources().getStringArray(R.array.main_list_pic);
        lst = new ArrayList<>();
        for (int i = 0; i < textArray.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("pic", picArray[i]);
            map.put("text", textArray[i]);
            lst.add(map);
        }
        mAdapter = new MainRecyclerViewAdapter(this, lst);
        mainRv.setAdapter(mAdapter);

        mAdapter.setOnclickListener(this);
    }

    @Override
    public void setupData() {

    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                gotoActivity(JokeEverydayActivity.class);
                break;
            case 1:
                gotoActivity(RecordPiaPiaActivity.class);
                break;
            case 2:
                gotoActivity(ChartsActivity.class);
                break;
            case 3:
                callIntent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "18602928514");
                callIntent.setData(data);
                if (Build.VERSION.SDK_INT >= 23) {
                    callPhone();
                } else {
                    startActivity(callIntent);
                }
                break;

        }


    }

    private void callPhone() {
        requestRuntimePermission(new String[]{android.Manifest.permission.CALL_PHONE}, new PermissionListener() {
            @Override
            public void onGranted() {
                startActivity(callIntent);
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                Toast.makeText(MainActivity.this, "您拒绝授权", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
