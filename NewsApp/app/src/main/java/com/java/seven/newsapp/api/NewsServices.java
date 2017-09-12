package com.java.seven.newsapp.api;

/**
 * Created by zzy on 17-9-8.
 */

import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NewsServices {
    @GET("latest")
    Observable<LatestNews> getLatestNews(@Query("category") int type, @Query("pageSize") int size, @Query("pageNo") int pageNo);

    @GET("search")
    Observable<LatestNews> getSearchNews(@Query("keyword") String keyword);

    @GET("detail")
    Observable<News> getDetailNews(@Query("newsId") String newsId);
}
