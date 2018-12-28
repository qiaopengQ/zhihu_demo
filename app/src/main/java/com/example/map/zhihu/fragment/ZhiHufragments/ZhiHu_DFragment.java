package com.example.map.zhihu.fragment.ZhiHufragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.zhihu.ZhiHuAdapter_hot;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.HotListBean;
import com.example.map.zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.map.zhihu.presenter.ZhiHuPresenter;
import com.example.map.zhihu.view.ZhiHuView;
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
public class ZhiHu_DFragment extends BaseFragment<ZhiHuView, ZhiHuPresenter<ZhiHuView>> implements ZhiHuView<String> {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private List<HotListBean.RecentBean> hot = new ArrayList<>();
    private ZhiHuAdapter_hot zhiHuAdapter_hot;

    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhi_hu__d;
    }

    @Override
    public void load() {
        super.load();
        presenter.getDailyListBean(ZhiHuRetrofit.HOT, null);
    }

    @Override
    protected void initData() {
        zhiHuAdapter_hot = new ZhiHuAdapter_hot(hot, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(zhiHuAdapter_hot);
    }

    @Override
    public ZhiHuPresenter<ZhiHuView> createPresenter() {
        return new ZhiHuPresenter<>();
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
    public void show(String s,ZhiHuRetrofit zhiHuRetrofit) {
        Log.e("ZhiHu_DFragment", s);
        Gson gson = new Gson();
        HotListBean hotListBean = gson.fromJson(s, HotListBean.class);
        List<HotListBean.RecentBean> recent = hotListBean.getRecent();
        hot.addAll(recent);
        zhiHuAdapter_hot.notifyDataSetChanged();
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
