package com.example.map.zhihu.fragment.GankFragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.ZhiHuiFragmentAdapter;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.GankListBean;
import com.example.map.zhihu.http.gank.GankRetri;
import com.example.map.zhihu.presenter.GankPresenter;
import com.example.map.zhihu.view.GankView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 干货集中营
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<GankView, GankPresenter<GankView>> implements GankView<String> {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private List<String> title = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private static List<String> gankimg = new ArrayList<>();

    @Override
    public int createLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initData() {
        presenter.getGankList(null,null, GankRetri.TECH_RANDOM);

        //Log.e("gankimg111", "gankimg:" + gankimg);

    }

    @Override
    public GankPresenter<GankView> createPresenter() {
        return new GankPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("GankFragment", s);
        GankListBean gankListBean = new Gson().fromJson(s, GankListBean.class);
        List<GankListBean.ResultsBean> results = gankListBean.getResults();
        Log.e("qweqweqweqw", "gankimg:" + results);
        for (int i = 0; i < results.size(); i++) {
            gankimg.add(results.get(i).getUrl());
        }
        title.add("Android");
        title.add("iOS");
        title.add("前端");
        title.add("福利");
        Gank_AFragment gank_aFragment = new Gank_AFragment(title.get(0),gankimg.get(0));
        Gank_BFragment gank_bFragment = new Gank_BFragment(title.get(1),gankimg.get(1));
        Gank_CFragment gank_cFragment = new Gank_CFragment(title.get(2),gankimg.get(2));
        Gank_DFragment gank_dFragment = new Gank_DFragment(title.get(3),gankimg.get(3));
        fragments.add(gank_aFragment);
        fragments.add(gank_bFragment);
        fragments.add(gank_cFragment);
        fragments.add(gank_dFragment);
        ZhiHuiFragmentAdapter zhiHuiFragmentAdapter = new ZhiHuiFragmentAdapter(getChildFragmentManager(), fragments, title);
        vp.setAdapter(zhiHuiFragmentAdapter);
        tab.setupWithViewPager(vp);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
