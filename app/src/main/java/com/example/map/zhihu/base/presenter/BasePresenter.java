package com.example.map.zhihu.base.presenter;

public interface BasePresenter<V> {
    //绑定view
    void attachView(V v);
    //解绑view
    void detachView();
}
