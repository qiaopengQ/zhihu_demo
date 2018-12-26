package com.example.map.zhihu.presenter;

import android.util.Log;

import com.example.map.zhihu.base.presenter.IBasePresenter;
import com.example.map.zhihu.model.WecharModel;
import com.example.map.zhihu.view.WecharView;

import java.util.Map;

public class WecharPresenter<V extends WecharView> extends IBasePresenter<V> implements WecharModel.WecharCallback{
    private WecharModel model = new WecharModel();
    public void getWecharList(){
        if (mView !=null){
            model.getWecharList(this);
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
