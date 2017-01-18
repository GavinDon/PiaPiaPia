package com.ln.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ln.entity.JokeListBean;
import com.ln.pia.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linan   on 2017/1/5.
 */

public class JokeListAdapter extends RecyclerView.Adapter<JokeListAdapter.ViewHolder> {
    JokeListBean jokeListBean;
    Context context;
    List<JokeListBean.ResultBean.DataBean> dataList;

    public JokeListAdapter(Context context, JokeListBean jokeListBean) {
        this.context = context;
        this.jokeListBean = jokeListBean;
        dataList = jokeListBean.getResult().getData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_joke, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvContent.setText(dataList.get(position).getContent());
        holder.updatetime.setText(dataList.get(position).getUpdatetime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.adp_joke_tv)
        TextView tvContent;
        @Bind(R.id.updatetime)
        TextView updatetime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
