package com.example.map.zhihu.fragment.GankFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.GankAdapter;
import com.example.map.zhihu.adapter.GankAdapter_fuli;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.GankListBean;
import com.example.map.zhihu.http.gank.GankRetri;
import com.example.map.zhihu.presenter.GankPresenter;
import com.example.map.zhihu.view.GankView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Gank_CFragment extends BaseFragment<GankView, GankPresenter<GankView>> implements GankView<String> {
    @BindView(R.id.img_android)
    ImageView imgAndroid;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private String tab;
    private List<GankListBean.ResultsBean> gank = new ArrayList<>();
    private GankAdapter_fuli gankAdapter_fuli;

    public Gank_CFragment() {
    }

    public Gank_CFragment(String tech, String img) {
        tab=tech;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_gank__c;
    }

    @Override
    public void load() {
        super.load();
        presenter.getGankList(tab, "1", GankRetri.TECH_ANDROID);
    }

    @Override
    protected void initData() {
        gankAdapter_fuli = new GankAdapter_fuli(gank, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(gankAdapter_fuli);
    }

    @Override
    public GankPresenter<GankView> createPresenter() {
        return new GankPresenter<>();
    }

    @Override
    public void show(String s) {
        Log.e("Gank_AFragment", s);
        GankListBean gankListBean = new Gson().fromJson(s, GankListBean.class);
        List<GankListBean.ResultsBean> results = gankListBean.getResults();
        gank.addAll(results);
        gankAdapter_fuli.notifyDataSetChanged();
        Log.e("gank", "gank:" + gank);
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
        // TODO: inflate a fragment view
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
