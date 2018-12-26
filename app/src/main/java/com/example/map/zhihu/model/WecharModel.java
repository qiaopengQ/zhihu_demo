package com.example.map.zhihu.model;

import android.util.Log;

import com.example.map.zhihu.base.model.HttpFinishCallback;
import com.example.map.zhihu.http.BaseObserver;
import com.example.map.zhihu.http.wechar.WecharManager;
import com.example.map.zhihu.utils.RxUtils;

import java.util.Map;

public class WecharModel {
    public interface WecharCallback<T> extends HttpFinishCallback{
        void setWecharList(T t);
    }
    public void getWecharList(final WecharCallback wecharCallback){
        wecharCallback.setShowProgressbar();
                WecharManager.getWxServer().getwxnew().compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(wecharCallback) {
                    @Override
                    public void onNext(String value) {
                        //Log.e("liangxq",value.toString());
                        //zhiHuCallback.setDailyListBean(value);
                        //Log.e("Qiaop", value);
                        wecharCallback.setWecharList(value);
                    }
                });
    }
    public void getWechar(){

    }
}
