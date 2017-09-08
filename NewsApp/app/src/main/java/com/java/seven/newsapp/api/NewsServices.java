package com.java.seven.newsapp.api;

/**
 * Created by zzy on 17-9-8.
 */

import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.News;
import com.java.seven.newsapp.bean.SearchNews;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NewsServices {
    @GET("latest?pageNo=1&pageSize=10")
    Observable<LatestNews> getLatestNews();

    @GET("search")
    Observable<LatestNews> getSearchNews(@Query("keyword") String keyword);

    @GET("detail")
    Observable<News> getDetailNews(@Query("newsId") String newsId);
}
