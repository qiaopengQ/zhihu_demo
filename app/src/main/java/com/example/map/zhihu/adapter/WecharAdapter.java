package com.example.map.zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.map.zhihu.R;
import com.example.map.zhihu.beans.WecharListBean;

import java.util.List;

public class WecharAdapter extends RecyclerView.Adapter<WecharAdapter.ViewHolder>{
    private List<WecharListBean.NewslistBean> wecharlist;
    private Context context;

    public WecharAdapter(List<WecharListBean.NewslistBean> wecharlist, Context context) {
        this.wecharlist = wecharlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_wechar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(wecharlist.get(position).getTitle());
        holder.tv_description.setText(wecharlist.get(position).getDescription());
        holder.tv_ctime.setText(wecharlist.get(position).getCtime());
        Glide.with(context).load(wecharlist.get(position).getPicUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return wecharlist.size();
    }
    public void setData(List<WecharListBean.NewslistBean> list){
        wecharlist.clear();
        wecharlist = list;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;
        private TextView tv_ctime;
        private TextView tv_description;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
            tv_ctime = itemView.findViewById(R.id.tv_ctime);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}
