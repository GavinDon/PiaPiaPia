package com.ln.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ln.db.RecordPiaDB;
import com.ln.pia.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linan   on 2017/1/12.
 */

public class ChartsAdapter extends RecyclerView.Adapter<ChartsAdapter.ChartsViewHolder> {

    private Context context;
    private List<RecordPiaDB> recordLst;


    public ChartsAdapter(Context context, List<RecordPiaDB> recordLst) {
        this.context = context;
        this.recordLst = recordLst;

    }
    public  void updateData(List<RecordPiaDB> lst){
        recordLst.clear();
        recordLst.addAll(lst);
        this.notifyDataSetChanged();
    }
    public void deleteData(){
        recordLst.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public ChartsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.adapter_charts_recycle,parent,false);
        return new ChartsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChartsViewHolder holder, int position) {
        holder.adaYufangCb.setChecked(recordLst.get(position).getYuFang());
        holder.adaDoubleCb.setChecked(recordLst.get(position).getDoublec());
        holder.adaLocationCharts.setText(recordLst.get(position).getLocation());
        holder.adaTime.setText(recordLst.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return recordLst.size();
    }


    class ChartsViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ada_location_charts)
        TextView adaLocationCharts;
        @Bind(R.id.ada_time)
        TextView adaTime;
        @Bind(R.id.ada_yufang_cb)
        AppCompatCheckBox adaYufangCb;
        @Bind(R.id.ada_double_cb)
        AppCompatCheckBox adaDoubleCb;

        public ChartsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
