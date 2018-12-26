package com.example.map.zhihu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.WecharAdapter;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.WecharListBean;
import com.example.map.zhihu.presenter.WecharPresenter;
import com.example.map.zhihu.view.WecharView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.GET;

/**
 * A simple {@link Fragment} subclass.
 */
public class WXFragment extends BaseFragment<WecharView, WecharPresenter<WecharView>> implements WecharView<String> {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private List<WecharListBean.NewslistBean> wecharlist = new ArrayList<>();
    private WecharAdapter wecharAdapter;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_wx;
    }

    @Override
    public void load() {
        super.load();
        /*HashMap<String, Object> map = new HashMap<>();
        map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
        map.put("num", "10");
        map.put("page", "1");*/
    }

    @Override
    protected void initData() {
        wecharAdapter = new WecharAdapter(wecharlist,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(wecharAdapter);
        presenter.getWecharList();
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void HideProgressbar() {

    }
    @Override
    public void showError(String error) {
        Log.e("WXFragment", error);
    }
    @Override
    public WecharPresenter<WecharView> createPresenter() {
        return new WecharPresenter<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void show(String s) {
        Log.e("WXFragment", s);
        Gson gson = new Gson();
        WecharListBean wecharListBean = gson.fromJson(s, WecharListBean.class);
        final List<WecharListBean.NewslistBean> newslist = wecharListBean.getNewslist();
        wecharlist.addAll(newslist);
        wecharAdapter.notifyDataSetChanged();
        smart.setEnableLoadMore(false);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh(1000);
                /*wecharlist.clear();
                wecharlist.addAll(newslist);
                wecharAdapter.notifyDataSetChanged();*/
            }
        });
    }
}
