package com.java.seven.newsapp.chinesenews.news;

import android.util.Log;

import com.java.seven.newsapp.api.NewsApi;
import com.java.seven.newsapp.bean.LatestNews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsModel implements NewsContract.Model {

    private static final String TAG = "NewsModel";
    final int MAX_SIZE = 50;
    List<LatestNews.ListBean> newsList;

    public NewsModel() { newsList = new ArrayList<>(1000); }

    @Override
    public void getLatestNews(final NewsContract.CallBackLatestNews callback, int size, int[] category) {
        final int size_ = Math.min(size, MAX_SIZE);
        Subscriber subscriber = new Subscriber<LatestNews>() {

            @Override
            public void onCompleted() {

                Log.d(TAG, "onCompleted: ");
//                final SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
//                Collections.sort(newsList, new Comparator<LatestNews.ListBean>() {
//                    @Override
//                    public int compare(LatestNews.ListBean listBean, LatestNews.ListBean t1) {
//                        try {
//                            Date time = sdf.parse(listBean.getNews_Time());
//                            Date time1 = sdf.parse(t1.getNews_Time());
//
//                            if (time1.before(time))
//                                return 1;
//                            else
//                                return -1;
//                        } catch (ParseException e) {
//                            return 0;
//                        }
//                    }
//                });
//                Log.d(TAG, newsList.get(0).getNews_Time());
//                Log.d(TAG, newsList.get(size_-1).getNews_Time());
                Collections.shuffle(newsList);
                callback.result(newsList.subList(0, size_));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(LatestNews latestNews) {
                Log.d(TAG, "onNext: ");
//                callback.result(latestNews.getList());
                newsList.addAll(latestNews.getList());
            }
        };
        newsList = new ArrayList<>(1000);
        NewsApi.getInstance().getLatestNews(subscriber, size_, category);
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