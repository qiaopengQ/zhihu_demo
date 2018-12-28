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
import android.widget.TextView;
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
import java.util.HashMap;
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
    @BindView(R.id.tv)
    TextView tv;
    private List<DailyListBean.StoriesBean> data = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private RecycleAdapter recycleAdapter;
    private boolean a = false;


    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhi_hu__a;
    }

    @Override
    public void load() {
        super.load();
        a = false;
        presenter.getDailyListBean(ZhiHuRetrofit.LATEST, null);
    }

    @Override
    protected void initData() {
        recycleAdapter = new RecycleAdapter(data, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(recycleAdapter);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDate(CalendarDay data) {
        int day = data.getDay()+1;//天
        int month = data.getMonth() + 1;//月
        int year = data.getYear();//年
        String date = null;
        HashMap<String, Object> map = new HashMap<>();
        if (day < 10) {
            date = year + "" + month + "" + "0" + day;
            map.put("date", date);
        } else {
            date = year + "" + month + "" + day;
            map.clear();
            map.put("date", date);
        }
        a = true;
        presenter.getDailyListBean(ZhiHuRetrofit.BEFORE, map);
        //Toast.makeText(getContext(), "data:" + date, Toast.LENGTH_SHORT).show();
        date = null;
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
        List<DailyListBean.StoriesBean> stories = dailyListBean.getStories();
        if (a = false) {
            tv.setVisibility(View.GONE);
            data.clear();
            data.addAll(stories);
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(dailyListBean.getDate());
            data.clear();
            data.addAll(stories);
        }
        recycleAdapter.notifyDataSetChanged();
        Log.e("ZhiHu_AFragment", "stories:" + stories);
        /*for (int i = 0; i < stories.size(); i++) {
            img.add(stories.get(i).getImages().get(0));
            title.add(stories.get(i).getTitle());
        }*/
        smart.setEnableLoadMore(false);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                /*data.clear();
                data.addAll(stories);*/
                //recycleAdapter.notifyDataSetChanged();
                a=false;
                presenter.getDailyListBean(ZhiHuRetrofit.LATEST, null);
                smart.finishRefresh();
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
        CircularAnimUtil.startActivity(getActivity(), intent, fabCalender, R.color.fab_bg);
    }
}
