package com.example.map.zhihu.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.map.zhihu.base.presenter.BasePresenter;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends SimpleFragment {
    public P presenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void load() {
        super.load();
        if (presenter==null){
            presenter = createPresenter();
            presenter.attachView((V) this);
        }
    }

    //创建指定的P层对象
    public abstract P createPresenter();
}
