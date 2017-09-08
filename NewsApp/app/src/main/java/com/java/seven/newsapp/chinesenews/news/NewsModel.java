package com.java.seven.newsapp.chinesenews.news;

import android.util.Log;

import com.java.seven.newsapp.api.NewsApi;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.SearchNews;

import rx.Subscriber;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsModel implements NewsContract.Model {

    private static final String TAG = "NewsModel";

    @Override
    public void getLatestNews(final NewsContract.CallBackLatestNews callback) {
        Subscriber subscriber = new Subscriber<LatestNews>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(LatestNews latestNews) {
                Log.d(TAG, "onNext: ");
                callback.result(latestNews.getList());
            }
        };
        NewsApi.getInstance().getLatestNews(subscriber);
    }

    @Override
    public void getSearchNews(final NewsContract.CallBackLatestNews callback, String keyword) {
        Subscriber subscriber = new Subscriber<LatestNews>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(LatestNews latestNews) {
                Log.d(TAG, "onNext: ");
                callback.result(latestNews.getList());
            }
        };
        NewsApi.getInstance().getSearchNews(subscriber, keyword);
    }

}