package com.example.map.zhihu.presenter;

import com.example.map.zhihu.base.presenter.IBasePresenter;
import com.example.map.zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.map.zhihu.model.ZhiHuModel;
import com.example.map.zhihu.view.ZhiHuView;

import java.util.Map;

public class ZhiHuPresenter<V extends ZhiHuView> extends IBasePresenter<V> implements ZhiHuModel.ZhiHuCallback{
    private ZhiHuModel zhiHuModel = new ZhiHuModel();
    public void getDailyListBean(ZhiHuRetrofit zhiHuRetrofit, Map<String,Object> map){
        if (mView !=null){
            zhiHuModel.getDailyListBean(this,zhiHuRetrofit,map);
        }
    }

    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }

    @Override
    public void setDailyListBean(Object o) {
        if (mView !=null){
            mView.show(o);
        }
    }
}
