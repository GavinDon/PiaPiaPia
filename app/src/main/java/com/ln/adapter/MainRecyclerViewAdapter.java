package com.ln.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ln.pia.R;
import com.ln.views.IconFontTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by linan   on 2017/1/5.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    List<Map<String, String>> lst = new ArrayList<>();
    private String colorArray[] = {"#FFE4E1", "#FF3E96", "#FF8C69", " #9932CC"};

    public MainRecyclerViewAdapter(Context context, List<Map<String, String>> lst) {
        this.mContext = context;
        this.lst = lst;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        1. 如果root为null，attachToRoot将失去作用，设置任何值都没有意义。
//        2. 如果root不为null，attachToRoot设为true，则会给加载的布局文件的指定一个父布局，即root。
//        3. 如果root不为null，attachToRoot设为false，则会将布局文件最外层的所有layout属性进行设置，当该view被添加到父view当中时，这些layout属性会自动生效。
//        4. 在不设置attachToRoot参数的情况下，如果root不为null，attachToRoot参数默认为true。
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_main_recylcerview, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPic.setText(lst.get(position).get("pic"));
        int num = new Random().nextInt(3);
        holder.tvPic.setTextColor(Color.parseColor(colorArray[num]));
        holder.tvText.setText(lst.get(position).get("text"));
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    @Override
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public interface OnClickListener {
        void onItemClick(View view, int position);
    }

    OnClickListener onClickListener;
    public void setOnclickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_pic)
        IconFontTextView tvPic;
        @Bind(R.id.tv_text)
        TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
