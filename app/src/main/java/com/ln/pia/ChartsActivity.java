package com.ln.pia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.ln.adapter.ChartsAdapter;
import com.ln.base.BaseActivity;
import com.ln.db.DbCore;
import com.ln.db.RecordPiaDB;
import com.ln.db.RecordPiaDBDao;
import com.ln.utils.DividerItemDecoration;
import com.ln.utils.MTools;
import com.ln.views.MyCalenderTheme;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.aigestudio.datepicker.bizs.calendars.DPCManager;
import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;


/**
 * 记录祥细信息
 */
public class ChartsActivity extends BaseActivity {
    @Bind(R.id.tv_notDataShow)
    TextView tvNotshow;
    @Bind(R.id.recycler)
    RecyclerView mRecyclerView;
    private RecordPiaDBDao dao;
    private ChartsAdapter mAdapter;
    private List<RecordPiaDB> recordLst = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_charts;
    }

    @Override
    public void initView() {
        super.initView();
        getToolbar("make love");
        dao = DbCore.getDaoSession().getRecordPiaDBDao();
        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        mAdapter = new ChartsAdapter(ChartsActivity.this, recordLst);
        List<RecordPiaDB> temp = dao.queryBuilder().where(RecordPiaDBDao.Properties.Date.eq(MTools.getDate())).list();
        if (!temp.isEmpty()) {
            tvNotshow.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.updateData(temp);
        }else {
            tvNotshow.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        initPicker();
    }

    private List<String> tmp;
    private DatePicker picker;


    private void initPicker() {
        picker = (DatePicker) findViewById(R.id.datePicker);
//        DPTManager.getInstance().initCalendar(new MyCalenderTheme());//自定义主题
        // 注意该方法必须调用，也就是说你必须为DatePicker指定一个确切年月
        picker.setDate(MTools.splitSystemDate()[0],
                MTools.splitSystemDate()[1] + 1);
        picker.setMode(DPMode.SINGLE);
        List<RecordPiaDB> temp = dao.queryBuilder().where(RecordPiaDBDao.Properties.Date.eq(MTools.getDate())).list();
        if (!temp.isEmpty()) {
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.updateData(temp);
        } else {
            mAdapter.deleteData();
        }
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {

                List<RecordPiaDB> temp = dao.queryBuilder().where(RecordPiaDBDao.Properties.Date.eq(date)).list();
                if (!temp.isEmpty()) {
                    tvNotshow.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.updateData(temp);
                } else {
                    mAdapter.deleteData();
                    mRecyclerView.setVisibility(View.GONE);
                    tvNotshow.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void setupData() {
        tmp = new ArrayList<>();
        List<RecordPiaDB> allList = dao.loadAll();
        for (int i = 0; i < allList.size(); i++) {
            tmp.add(allList.get(i).getDate());
        }
        DPCManager.getInstance().setDecorTL(tmp);
        picker.setDPDecor(new DPDecor() {
            @Override
            public void drawDecorTL(Canvas canvas, Rect rect, Paint paint, String data) {
                paint.setColor(Color.parseColor("#9bcb67"));
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 3F, paint);
            }
        });

    }
}
