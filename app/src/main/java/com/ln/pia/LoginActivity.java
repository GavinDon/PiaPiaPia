package com.ln.pia;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ln.base.BaseActivity;
import com.ln.entity.LoginInBean;
import com.ln.https.APIS;
import com.ln.https.RetrofitHelper;
import com.ln.subscribers.ProgressSubscriber;
import com.ln.subscribers.SubscriberOnNextListener;
import com.ln.views.IconFontTextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.et)
    EditText et;
    @Bind(R.id.et2)
    EditText et2;
    @Bind(R.id.my_toolbar)
    Toolbar myToolbar;
    @Bind(R.id.ti_1)
    TextInputLayout ti1;
    @Bind(R.id.ti_2)
    TextInputLayout ti2;
    @Bind(R.id.btn_login)
    IconFontTextView btnLogin;

    private String strPsw;
    private String strAccount;

    @Override
    public int setLayout() {
        return R.layout.activity_show_calender;
    }

    @Override
    public void initView() {
        super.initView();
        getToolbar("登录");
        et.setText("18602928514");
        et2.setText("123456");
    }

    @Override
    public void setupData() {

    }

    @OnClick({R.id.et2, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                strAccount = et.getText().toString().trim();
                strPsw = et2.getText().toString().trim();
                if (TextUtils.isEmpty(strAccount)) {
                    Toast.makeText(this, "帐号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(strPsw)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    loginReq();
                }
                break;
        }
    }

    /**
     * 帐号登录
     */
    private void loginReq() {
        Map<String, String> params = new HashMap<>();
        params.put("username", strAccount);
        params.put("password", strPsw);
        params.put("imsi", "123456");
        params.put("mobileLogin", "true");
        params.put("type", "0");
        RetrofitHelper.getInstance().creat(APIS.class).loginIn(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<LoginInBean>(new SubscriberOnNextListener<LoginInBean>() {
                    @Override
                    public void onNext(LoginInBean LoginBean) {
                        if (TextUtils.isEmpty(LoginBean.getId())) {
                            Toast.makeText(LoginActivity.this, LoginBean.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            gotoActivity(MainActivity.class, true);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, this));

    }
}
