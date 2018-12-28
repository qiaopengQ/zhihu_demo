package com.example.map.zhihu.view;

import com.example.map.zhihu.base.view.BaseView;
import com.example.map.zhihu.beans.DailyListBean;
import com.example.map.zhihu.http.zhihu.ZhiHuRetrofit;

public interface ZhiHuView<T> extends BaseView{
    void show(T t, ZhiHuRetrofit zhiHuRetrofit);
}
