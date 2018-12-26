package com.example.map.zhihu.fragment.ZhiHufragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.map.zhihu.R;
import com.example.map.zhihu.activity.ZhiHuActivity;
import com.example.map.zhihu.adapter.RecycleAdapter;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.DailyListBean;
import com.example.map.zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.map.zhihu.presenter.ZhiHuPresenter;
import com.example.map.zhihu.utils.CircularAnimUtil;
import com.example.map.zhihu.view.ZhiHuView;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHu_AFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView<String> {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    Unbinder unbinder;
    CalendarDay s;
    private List<DailyListBean.StoriesBean> data = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private RecycleAdapter recycleAdapter;


    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhi_hu__a;
    }

    @Override
    public void load() {
        super.load();
        presenter.getDailyListBean(ZhiHuRetrofit.LATEST, null);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDate(CalendarDay data){
        Toast.makeText(getContext(), "data:" + data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void HideProgressbar() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void show(String o) {
        Gson gson = new Gson();
        DailyListBean dailyListBean = gson.fromJson(o, DailyListBean.class);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        final List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
        Log.e("ZhiHu_AFragment", "stories:" + stories);
        for (int i = 0; i < stories.size(); i++) {
            img.add(stories.get(i).getImages().get(0));
            title.add(stories.get(i).getTitle());
        }
        data.addAll(stories);
        recycleAdapter = new RecycleAdapter(data, getContext());
        rv.setAdapter(recycleAdapter);
        rv.setLayoutManager(manager);
        recycleAdapter.notifyDataSetChanged();
        smart.setEnableLoadMore(false);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                data.clear();
                data.addAll(stories);
                recycleAdapter.notifyDataSetChanged();
                smart.finishRefresh(1000);
            }
        });
        /*banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//设置图片加载器，图片加载器在下方
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //path表示当前要显示的图片的url，，imageView表示当前要显示图片的载体
                Glide.with(context).load(path).into(imageView);
            }
        });
//设置图片网址或地址的集合
        banner.setImages(img);
//设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
//设置轮播图的标题集合
        banner.setBannerTitles(title);
//设置轮播间隔时间
        banner.setDelayTime(3000);
//设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
//设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(6);
        banner.start();*/

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab_calender)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(getContext(), ZhiHuActivity.class);
        CircularAnimUtil.startActivity(getActivity(),intent,fabCalender,R.color.fab_bg);
    }
}
