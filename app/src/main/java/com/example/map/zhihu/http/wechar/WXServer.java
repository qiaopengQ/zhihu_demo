package com.example.map.zhihu.http.wechar;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WXServer {
    String URL = "http://api.tianapi.com/wxnew/";
    //?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
    /*@GET("?")
    Observable<String> getwxnew(@QueryMap Map<String,Object> map);*/

    @GET("?")
    Observable<String> getwxnew(@QueryMap Map<String,Object> map);
}
