package com.ln.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.ln.db.DbCore;

/**
 * Created by linan   on 2016/12/29.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        DbCore.init(this.getApplicationContext());
    }
}
