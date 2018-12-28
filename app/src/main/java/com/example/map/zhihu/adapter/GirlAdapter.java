package com.example.map.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.map.zhihu.R;
import com.example.map.zhihu.beans.GankListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.ViewHolder>{
    private List<GankListBean.ResultsBean> mList;
    private Context context;

    public GirlAdapter(List<GankListBean.ResultsBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    /**
     * 在StaggeredGridLayoutManager瀑布流中,当需要依据图片实际相对高度,不断动态设置ImageView的LayoutParams时,
     * 会导致快速滑动状态下产生重新排列,重写getItemViewType并设置StaggeredGridLayoutManager.GAP_HANDLING_NONE解决了这个问题，原因目前未知
     * https://github.com/oxoooo/mr-mantou-android/blob/master/app/src/main/java/ooo/oxo/mr/MainAdapter.java
     *
     * @return
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_girl, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        /*//存在记录的高度时先Layout再异步加载图片
        if (mList.get(holder.getAdapterPosition()).getHeight() > 0) {
            ViewGroup.LayoutParams layoutParams = holder.ivGirl.getLayoutParams();
            layoutParams.height = mList.get(holder.getAdapterPosition()).getHeight();
        }*/

        Glide.with(context).load(mList.get(position).getUrl()).into(holder.ivGirl);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_girl)
        ImageView ivGirl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
