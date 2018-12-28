package com.example.map.zhihu.presenter;

import android.util.Log;

import com.example.map.zhihu.base.presenter.IBasePresenter;
import com.example.map.zhihu.http.wechar.WecharRetro;
import com.example.map.zhihu.model.WecharModel;
import com.example.map.zhihu.view.WecharView;

import java.util.Map;

public class WecharPresenter<V extends WecharView> extends IBasePresenter<V> implements WecharModel.WecharCallback{
    private WecharModel model = new WecharModel();
    public void getWecharList(Map<String,Object> map){
        if (mView !=null){
            model.getWecharList(this,map);
        }
    }
    @Override
    public void setWecharList(Object o) {
        if (mView!=null){

            mView.show(o);
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
        if (mView !=null){
            mView.showError(error);
        }
    }
}
