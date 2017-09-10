package com.java.seven.newsapp.chinesenews.favorate;

import android.util.Log;

import com.java.seven.newsapp.api.NewsApi;
import com.java.seven.newsapp.bean.FavorNews;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.News;

import org.jsoup.parser.Tag;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by zzy on 17-9-10.
 */

public class FavorModel implements FavorContract.Model {
    private static  final String TAG = "FavorModel";
    List<LatestNews.ListBean> favorList;

    @Override
    public void getFavor(final FavorContract.CallBackFavor callback) {
        favorList = new ArrayList<>();
        Subscriber subscriber = new Subscriber<LatestNews.ListBean>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
                callback.result(favorList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(LatestNews.ListBean listBean) {
                favorList.add(listBean);
                Log.d(TAG, "onNext: ");
            }
        };
        List<FavorNews> favors = DataSupport.findAll(FavorNews.class);
        HashSet<String> hashSet = new HashSet<>();
        for (FavorNews news : favors)
            hashSet.add(news.getNewsId());
        String[] ids = new String[hashSet.size()];
        hashSet.toArray(ids);
        NewsApi.getInstance().getNews(subscriber, ids);
    }
}