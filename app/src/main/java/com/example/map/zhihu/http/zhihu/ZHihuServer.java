package com.example.map.zhihu.http.zhihu;

import com.example.map.zhihu.beans.DailyListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名：MyProject
 * 包名：  com.liangxq.mydemo.http
 * 文件名：ZHihuServer
 * 创建者：liangxq
 * 创建时间：2018/12/21  8:47
 * 描述：TODO
 */
public interface ZHihuServer {
    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
//    @GET("start-image/{res}")
//    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    /**
     * 最新日报DailyListBean
     */
    @GET("news/latest")
    Observable<String> getDailyList();

    /**
     * 往期日报DailyBeforeListBean
     */
    @GET("news/before/{date}")
    Observable<String> getDailyBeforeList(@Path("date") String date);

    /**
     * 主题日报ThemeListBean
     */
    @GET("themes")
    Observable<String> getThemeList();

    /**
     * 主题日报详情ThemeChildListBean
     */
    @GET("theme/{id}")
    Observable<String> getThemeChildList(@Path("id") int id);

    /**
     * 专栏日报SectionListBean
     */
    @GET("sections")
    Observable<String> getSectionList();

    /**
     * 专栏日报详情SectionChildListBean
     */
    @GET("section/{id}")
    Observable<String> getSectionChildList(@Path("id") int id);

    /**
     * 热门日报HotListBean
     */
    @GET("news/hot")
    Observable<String> getHotList();

    /**
     * 日报详情ZhihuDetailBean
     */
    @GET("news/{id}")
    Observable<String> getDetailInfo(@Path("id") int id);

    /**
     * 日报的额外信息DetailExtraBean
     */
    @GET("story-extra/{id}")
    Observable<String> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报的长评论CommentBean
     */
    @GET("story/{id}/long-comments")
    Observable<String> getLongCommentInfo(@Path("id") int id);

    /**
     * 日报的短评论CommentBean
     */
    @GET("story/{id}/short-comments")
    Observable<String> getShortCommentInfo(@Path("id") int id);

}
