package com.ln.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ln.pia.R;


/**
 * 类作用，加载数据对话框
 * 展示数据对话框
 * Created by linan   on 2016/11/28.
 */
public class SweetDialog extends Dialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final int NORMAL_TYPE = 0;
    public static final int ERROR_TYPE = 1;
    public static final int SUCCESS_TYPE = 2;
    public static final int WARNING_TYPE = 3;
    public static final int CUSTOM_IMAGE_TYPE = 4;
    public static final int PROGRESS_TYPE = 5;
    private Context mcontext;
    private Button btnConfirm;
    private Button btnCancle;
    private ImageView leftImage;
    private ImageView topImage;
    private TextView tvContent;
    private AppCompatCheckBox cbDouble;
    private AppCompatCheckBox cbYufang;

    public SweetDialog(Context context) {
        this(context, NORMAL_TYPE);
        this.mcontext = context;
    }

    public SweetDialog(Context context, int alertType) {
        super(context, R.style.sweet_dialog);
        show(); //调用 onCreate 方法
    }


    /**
     * 加载布局文件
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window mWindow = getWindow();
        mWindow.setContentView(R.layout.sweet_dialog);
        mWindow.setWindowAnimations(R.style.sweet_dialog_anim);
        this.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams wl = mWindow.getAttributes();
        mWindow.setAttributes(wl);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        btnCancle = (Button) findViewById(R.id.btn_cancle);
        topImage = (ImageView) findViewById(R.id.topImage);
        leftImage = (ImageView) findViewById(R.id.leftImage);
        tvContent = (TextView) findViewById(R.id.content);
        cbDouble= (AppCompatCheckBox) findViewById(R.id.double_checkbox);
        cbYufang= (AppCompatCheckBox) findViewById(R.id.yufang_checkbox);
        cbYufang.setOnCheckedChangeListener(this);
        cbDouble.setOnCheckedChangeListener(this);
        btnConfirm.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
        loadAnimation();


    }

    private void loadAnimation() {
//        Animation anim=AnimationUtils.loadAnimation(mcontext,R.anim.success_bow_roate);
//        WindowManager.LayoutParams wl=getWindow().getAttributes();
//        wl.alpha=1.0f;
    }

    /**
     * 设置对话框显示内容
     *
     * @param content
     * @return
     */
    public SweetDialog setContent(String content) {
        if (tvContent != null && content != null) {
            tvContent.setText(content);
        }
        return this;
    }

    public SweetDialog setLeftIsShow(boolean isGone) {
        if (isGone) {
            leftImage.setVisibility(View.VISIBLE);
        } else {
            leftImage.setVisibility(View.GONE);
        }

        return this;
    }


    public SweetDialog setTopImage(int resId) {
        topImage.setImageResource(resId);
        return this;
    }

    public SweetDialog setLeftImage(int resId) {
        leftImage.setImageResource(resId);
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                listener.onSweetConfirm(yufangCk,doubleCk);
                break;
            case R.id.btn_cancle:
                listener.onSweetCancle();
                break;
        }
    }
    private boolean doubleCk;
    private boolean yufangCk;
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.double_checkbox:
                doubleCk=isChecked;
                break;
            case R.id.yufang_checkbox:
                yufangCk=isChecked;
                break;
        }
    }


    public interface OnSweetListener {
        void onSweetConfirm(boolean yufang,boolean doubleCk);

        void onSweetCancle();
    }

    private OnSweetListener listener;

    public SweetDialog setOnSweetListener(OnSweetListener listener) {
        this.listener = listener;
        return this;
    }


}
