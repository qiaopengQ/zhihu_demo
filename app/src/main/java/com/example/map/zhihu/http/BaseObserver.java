package com.example.map.zhihu.http;

import android.util.Log;

import com.example.map.zhihu.base.model.HttpFinishCallback;

import java.util.logging.Logger;

import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T>{
    //回调结果处理
    private HttpFinishCallback httpFinishCallback;
    public BaseObserver(HttpFinishCallback httpFinishCallback){
        this.httpFinishCallback = httpFinishCallback;
    }
    //管理内存网络请求
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        Log.e("liangxq",e.getMessage());
        if (compositeDisposable !=null){
            compositeDisposable.clear();
        }
        if (httpFinishCallback!=null){
            if (e instanceof HttpException){
                httpFinishCallback.setError("网络请求错误");
            }else {
                httpFinishCallback.setError("其他请求错误");
            }
            Logger.getLogger(e.getMessage());
            httpFinishCallback.setHideProgressbar();
        }
    }

    @Override
    public void onComplete() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
        if (httpFinishCallback !=null){
            httpFinishCallback.setHideProgressbar();
        }
    }
}
