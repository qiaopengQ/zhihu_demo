package com.example.map.zhihu.model;

import com.example.map.zhihu.base.model.HttpFinishCallback;
import com.example.map.zhihu.http.BaseObserver;
import com.example.map.zhihu.http.zhihu.ZhiHuRetrofit;
import com.example.map.zhihu.http.zhihu.ZhihuManager;
import com.example.map.zhihu.utils.RxUtils;

import java.util.Map;

public class ZhiHuModel{
    public interface ZhiHuCallback<T> extends HttpFinishCallback{
        void setDailyListBean(T t,ZhiHuRetrofit zhiHuRetrofit);
    }
    public void getDailyListBean(final ZhiHuCallback zhiHuCallback, ZhiHuRetrofit zhiHuRetrofit, Map<String,Object> map){
        zhiHuCallback.setShowProgressbar();
        switch (zhiHuRetrofit){
            case LATEST:
                //日报
                ZhihuManager.getzHihuServer().getDailyList().compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(zhiHuCallback) {
                    @Override
                    public void onNext(String value) {
//                Log.e("liangxq",value.toString());
                        zhiHuCallback.setDailyListBean(value,ZhiHuRetrofit.LATEST);
                    }
                });
                break;
            case BEFORE:
                //往期
                String date = (String) map.get("date");
                ZhihuManager.getzHihuServer().getDailyBeforeList(date).compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(zhiHuCallback) {
                    @Override
                    public void onNext(String value) {
//                Log.e("liangxq",value.toString());
                        zhiHuCallback.setDailyListBean(value,ZhiHuRetrofit.BEFORE);
                    }
                });
                break;
            case SECTIONS:
                //专栏
                ZhihuManager.getzHihuServer().getSectionList().compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(zhiHuCallback) {
                    @Override
                    public void onNext(String value) {
                        //  Log.e("liangxq",value.toString());
                        zhiHuCallback.setDailyListBean(value,ZhiHuRetrofit.SECTIONS);
                    }
                });
                break;
            case SECTIONSINFO:
                //专栏详情
                break;
            case HOT:
                //热门
                ZhihuManager.getzHihuServer().getHotList().compose(RxUtils.<String>rxObserbaleSchedulerHelper()).subscribe(new BaseObserver<String>(zhiHuCallback) {
                    @Override
                    public void onNext(String value) {
                        //  Log.e("liangxq",value.toString());
                        zhiHuCallback.setDailyListBean(value,ZhiHuRetrofit.HOT);
                    }
                });
                break;
            case HOTINFO:
                //热门详情
                break;
        }

    }
    public void getData(){

    }
}
