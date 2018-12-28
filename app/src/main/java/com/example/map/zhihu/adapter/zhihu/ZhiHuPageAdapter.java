package com.example.map.zhihu.adapter.zhihu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.map.zhihu.R;
import com.example.map.zhihu.beans.DailyListBean;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class ZhiHuPageAdapter extends PagerAdapter{
    private List<DailyListBean.TopStoriesBean> list;
    private Context context;

    public ZhiHuPageAdapter(List<DailyListBean.TopStoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_page, container, false);
        ImageView iv_top_image = view.findViewById(R.id.iv_top_image);
        TextView tv_top_title = view.findViewById(R.id.tv_top_title);
        //ImageLoader.load(container,list.get(position).getImage(),iv_top_image);
        Glide.with(context).load(list.get(position).getImage()).into(iv_top_image);
        tv_top_title.setText(list.get(position).getTitle());
        int id = list.get(position).getId();
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
