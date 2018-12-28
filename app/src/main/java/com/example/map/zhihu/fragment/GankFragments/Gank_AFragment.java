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

import com.bumptech.glide.Glide;
import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.GankAdapter;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.GankListBean;
import com.example.map.zhihu.http.gank.GankRetri;
import com.example.map.zhihu.presenter.GankPresenter;
import com.example.map.zhihu.utils.SystemUtil;
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


public class Gank_AFragment extends BaseFragment<GankView, GankPresenter<GankView>> implements GankView<String> {

    @BindView(R.id.img_android)
    ImageView imgAndroid;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private List<GankListBean.ResultsBean> gank = new ArrayList<>();
    private String tab;
    private String img;
    private GankAdapter gankAdapter;

    @SuppressLint("ValidFragment")
    public Gank_AFragment(String tech, String img) {
        tab = tech;
        this.img=img;
        //Toast.makeText(getContext(), img+"", Toast.LENGTH_SHORT).show();

        Log.e("tab", img);
    }


    public Gank_AFragment() {

    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_gank__a;
    }

    @Override
    protected void initData() {
        Glide.with(this).load(img).into(imgAndroid);
        techAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float rate = (SystemUtil.dp2px(getContext(),256)+verticalOffset *2)/ SystemUtil.dp2px(getContext(),256);
                if (rate >=0){
                    imgAndroid.setAlpha(rate);
                }
            }
        });
        gankAdapter = new GankAdapter(gank, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(gankAdapter);

    }

    @Override
    public void load() {
        super.load();
        presenter.getGankList(tab, "1", GankRetri.TECH_ANDROID);
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
        gankAdapter.notifyDataSetChanged();
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
