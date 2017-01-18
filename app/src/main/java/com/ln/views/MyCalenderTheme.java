package com.ln.views;

import android.graphics.Color;

import cn.aigestudio.datepicker.bizs.themes.DPTheme;

/**
 * Created by linan   on 2017/1/11.
 */

public class MyCalenderTheme extends DPTheme {
    private int colorTitle= Color.parseColor("#FF69C0");
    private int colorTitles= Color.parseColor("#ffffff");
    @Override
    public int colorBG() {
        return 0;
    }

    @Override
    public int colorBGCircle() {
        return 0;
    }

    @Override
    public int colorTitleBG() {
        return 0;
    }

    @Override
    public int colorTitle() {
        return colorTitles;
    }

    @Override
    public int colorToday() {
        return 0;
    }

    @Override
    public int colorG() {
        return 0;
    }

    @Override
    public int colorF() {
        return 0;
    }

    @Override
    public int colorWeekend() {
        return 0;
    }

    @Override
    public int colorHoliday() {
        return 0;
    }
}
