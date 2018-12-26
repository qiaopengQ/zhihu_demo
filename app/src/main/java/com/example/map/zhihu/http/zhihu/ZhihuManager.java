package com.example.map.zhihu.http.zhihu;

import com.example.map.zhihu.http.HttpManager;

import io.reactivex.Observable;

public class ZhihuManager {
    private static ZHihuServer zHihuServer;
    public static ZHihuServer getzHihuServer(){
        if (zHihuServer  == null){
            synchronized (ZHihuServer.class){
                if (zHihuServer==null){
                    zHihuServer = HttpManager.getInstance().getServer(ZHihuServer.HOST,ZHihuServer.class);
                }
            }
        }
        return zHihuServer;
    }
    public Observable<String> getDailyList(){
        return zHihuServer.getDailyList();
    }

    public Observable<String> getSectionList(){
        return zHihuServer.getSectionList();
    }
}
