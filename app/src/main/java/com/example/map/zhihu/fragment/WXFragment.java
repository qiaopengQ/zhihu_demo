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
import android.widget.Toast;

import com.example.map.zhihu.MainActivity;
import com.example.map.zhihu.R;
import com.example.map.zhihu.adapter.WecharAdapter;
import com.example.map.zhihu.base.fragment.BaseFragment;
import com.example.map.zhihu.beans.WecharListBean;
import com.example.map.zhihu.http.wechar.WecharRetro;
import com.example.map.zhihu.presenter.WecharPresenter;
import com.example.map.zhihu.view.WecharView;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private boolean a = true;
    private HashMap<String, Object> map = new HashMap<>();

    @Override
    public int createLayoutId() {
        return R.layout.fragment_wx;
    }

    @Override
    protected void initData() {
        wecharAdapter = new WecharAdapter(wecharlist, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        rv.setAdapter(wecharAdapter);
        if (a) {
            map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
            map.put("num", "10");
            map.put("page", "1");
            presenter.getWecharList(map);
        }
        MainActivity.view_search.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
                map.put("num", "10");
                map.put("page", "1");
                map.put("word", query);
                presenter.getWecharList(map);
                a=false;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        smart.setEnableLoadMore(false);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
              /*  if (a == false) {
                    wecharlist.clear();
                    Initialize();
                } else {
                    SeekMap(q);
                }*/
                map.clear();
                map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
                map.put("num", "10");
                map.put("page", "1");
                presenter.getWecharList(map);
                wecharAdapter.notifyDataSetChanged();
                smart.finishRefresh();
                Toast.makeText(getContext(), "刷新完毕!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //初始化数据
    public void Initialize() {
        map.put("key", "52b7ec3471ac3bec6846577e79f20e4c");
        map.put("num", "10");
        map.put("page", "1");
        //a=false;
        presenter.getWecharList(map);
    }

    //搜索数据

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
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void show(String s) {
        Gson gson = new Gson();
        WecharListBean wecharListBean = gson.fromJson(s, WecharListBean.class);
        List<WecharListBean.NewslistBean> newslist = wecharListBean.getNewslist();
        Log.e("初始化", "newslist:" + newslist);
        if (a){
            wecharAdapter.setData(newslist);
        }else {
            wecharAdapter.setData(newslist);
        }
        /*wecharlist.clear();
        wecharlist.addAll(newslist);
        Log.e("WXFragment", "a:" + a);
        Log.e("数据", "newslist:" + newslist);
        wecharAdapter.notifyDataSetChanged();*/
    }
}
