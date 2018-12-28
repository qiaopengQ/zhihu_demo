package com.example.map.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.map.zhihu.R;
import com.example.map.zhihu.beans.GankListBean;

import java.util.List;

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder>{
    private List<GankListBean.ResultsBean> list;
    private Context context;

    public GankAdapter(List<GankListBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gankitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_desc.setText(list.get(position).getDesc());
        holder.tv_who.setText(list.get(position).getWho());
        holder.tv_time.setText(list.get(position).getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_desc;
        private TextView tv_who;
        private TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_who = itemView.findViewById(R.id.tv_who);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
