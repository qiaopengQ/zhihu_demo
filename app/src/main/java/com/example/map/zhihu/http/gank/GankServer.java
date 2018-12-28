package com.example.map.zhihu.http.gank;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankServer {
    String GANK = "http://gank.io/api/";
    //https://gank.io/api/data/Android/10/1
    //初始化显示数据
    @GET("data/{tech}/20/{page}")
    Observable<String> getTech(@Path("tech") String tech,@Path("page")String page);
    //随机妹子
    @GET("random/data/福利/20")
    Observable<String> getRandom();
    //http://gank.io/api/search/query/{搜索字段}/category/Android/count/10/page/1
    //搜索接口
    @GET("search/query/{seek}/category/{tab}/count/10/page/{page}")
    Observable<String> getCategory(@Path("seek")String seek,@Path("tab")String tab,@Path("page")String page);
}
