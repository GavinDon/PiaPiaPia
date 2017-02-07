package com.ln.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.bugtags.library.Bugtags;
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
        Bugtags.start("23e8fd50a1de18bc74e59f838e69924f", this, Bugtags.BTGInvocationEventBubble);
    }
}
