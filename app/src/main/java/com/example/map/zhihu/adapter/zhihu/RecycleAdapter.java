package com.example.map.zhihu.adapter.zhihu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.util.DiffUtil;

import com.bumptech.glide.Glide;
import com.example.map.zhihu.R;
import com.example.map.zhihu.beans.DailyListBean;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<DailyListBean.StoriesBean> list ;
    private List<DailyListBean.TopStoriesBean> list2;
    private Context context;
    private Boolean isBefore = false;
    private String tv_name = "今日热闻";
    private ZhiHuPageAdapter zhiHuPageAdapter;
    private ViewPager topViewPager;

    public RecycleAdapter(List<DailyListBean.StoriesBean> list,List<DailyListBean.TopStoriesBean> list2, Context context) {
        this.list = list;
        this.list2 = list2;
        this.context = context;
    }
    // 0  viewpage    1 textview      2    recycleadapter
    @Override
    public int getItemViewType(int position) {
        if(isBefore){
            if(position==0){
                return 1;
            }else{
                return 2;
            }
        }else{
            if (position == 0){
                return 0;
            }else if (position == 1){
                return 1;
            }else {
                return 2;
            }
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == 0){
            zhiHuPageAdapter = new ZhiHuPageAdapter(list2,context);
            View view0 = LayoutInflater.from(context).inflate(R.layout.item_zhihua,parent,false);
            ViewHolder0 viewHolder0 = new ViewHolder0(view0);
            return viewHolder0;
        }else if (viewType ==1){
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_zhihub, parent,false);
            ViewHolder1 viewHolder1 = new ViewHolder1(view1);
            return viewHolder1;
        }else {
            View view2 = LayoutInflater.from(context).inflate(R.layout.item_zhihuc, parent,false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view2);
            return viewHolder2;
        }
        /*View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);*/
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder2){
            final int contentPosition;
            if (isBefore){
                contentPosition = position -1;
            }else {
                contentPosition = position -2;
            }
            ((ViewHolder2) holder).tv.setText(list.get(contentPosition).getTitle());
            Glide.with(context).load(list.get(position).getImages().get(0)).into(((ViewHolder2) holder).img);
        }else if (holder instanceof ViewHolder1){
            ((ViewHolder1) holder).tv_daily_date.setText(tv_name);
        }else {
            ((ViewHolder0)holder).vp.setAdapter(zhiHuPageAdapter);
            topViewPager = ((ViewHolder0) holder).vp;
        }
        int itemViewType = getItemViewType(position);
        /*if (itemViewType == 0){
            ViewHolder0 viewHolder0 = (ViewHolder0) holder;
        }else if (itemViewType ==1){
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
        }else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
        }*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //最新数据
    public void addDailyList(DailyListBean daily){
        tv_name = "今日热闻";
        isBefore = false;
        list = daily.getStories();
        list2 = daily.getTop_stories();
        //DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new )
    }
    //往期数据
    public void addDailyBeforeList(DailyListBean daily){
        isBefore = true;
        tv_name = daily.getDate();
        list = daily.getStories();
        list2 = daily.getTop_stories();
    }
    public class ViewHolder0 extends RecyclerView.ViewHolder {

        private ViewPager vp;

        public ViewHolder0(View itemView) {
            super(itemView);
            vp = itemView.findViewById(R.id.vp);
        }
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView tv_daily_date;

        public ViewHolder1(View itemView) {
            super(itemView);
            tv_daily_date = itemView.findViewById(R.id.tv_daily_date);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        private TextView tv;
        private ImageView img;

        public ViewHolder2(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
