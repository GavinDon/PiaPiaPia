package com.ln.pia;

import android.widget.ImageView;

import com.ln.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SplashActivity extends BaseActivity {


    @Bind(R.id.splash_image)
    ImageView splashImage;

    @Override
    public int setLayout() {

        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        //延时3秒来打开主界面
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                      gotoActivity(LoginActivity.class,true);
                    }
                });

    }

    @Override
    public void setupData() {

    }

}
