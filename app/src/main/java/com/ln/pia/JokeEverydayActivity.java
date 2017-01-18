package com.ln.pia;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.ln.adapter.JokeListAdapter;
import com.ln.base.BaseActivity;
import com.ln.entity.JokeListBean;
import com.ln.https.APIS;
import com.ln.https.RetrofitHelper;
import com.ln.subscribers.ProgressSubscriber;
import com.ln.subscribers.SubscriberOnNextListener;
import com.ln.utils.DividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 每日一笑
 */
public class JokeEverydayActivity extends BaseActivity {
    @Bind(R.id.joke_recycler)
    RecyclerView jokeRecycler;
    @Bind(R.id.joke_swiperefresh)
    SwipeRefreshLayout jokeSwiperefresh;
    private Toolbar mToolBar;
    private JokeListAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_joke_everyday;
    }

    @Override
    public void initView() {
        super.initView();
        mToolBar = getToolbar("每日一笑");
        LinearLayoutManager lm = new LinearLayoutManager(this);
        jokeRecycler.setLayoutManager(lm);
        jokeListReq();


    }

    private void jokeListReq() {
        long time1 = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));//当前时间戳
        Map<String, Object> map = new HashMap<>();
        map.put("key", "f627df0555c5559a3c7e46aa7dfc666c");
        map.put("page", 1);
        map.put("pagesize", 20);
        map.put("time", time1);
        map.put("sort", "desc");
        RetrofitHelper.getInstance().creat(APIS.class).getJokeList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<JokeListBean>(new SubscriberOnNextListener<JokeListBean>() {
                    @Override
                    public void onNext(JokeListBean joke) {
                        if (joke.getError_code() == 0) {
                            mAdapter = new JokeListAdapter(JokeEverydayActivity.this, joke);
                            jokeRecycler.setAdapter(mAdapter);
                        } else {
                            Toast.makeText(JokeEverydayActivity.this, joke.getReason(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(JokeEverydayActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, this));
    }

    @Override
    public void setupData() {
    }


}
