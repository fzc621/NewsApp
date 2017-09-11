package com.java.seven.newsapp.chinesenews.news;

import android.util.Log;

import com.java.seven.newsapp.api.NewsApi;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.SimpleNews;
import com.java.seven.newsapp.util.TestNetwork;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsModel implements NewsContract.Model {

    private static final String TAG = "NewsModel";
    final int MAX_SIZE = 50;
    List<LatestNews.ListBean> newsList;
    List<LatestNews.ListBean> currentList;
    Set<String> newsIdSet;
    int pageNo = 1;

    public NewsModel() {
        newsList = new ArrayList<>(1000);
        currentList = new ArrayList<>();
        newsIdSet = new HashSet<>();
    }

    @Override
    public void getLatestNews(final NewsContract.CallBackLatestNews callback, int size, final int[] category) {
        final int size_ = Math.min(size, MAX_SIZE);
        if (TestNetwork.isNetworkAvailable()) {
            Subscriber subscriber = new Subscriber<LatestNews>() {

                @Override
                public void onCompleted() {

                    Log.d(TAG, "onCompleted: ");
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
            NewsApi.getInstance().getLatestNews(subscriber, size_, 1, category);
        }
        else {
            List<Integer> array = new ArrayList<>();
            for (int i : category)
                array.add(i);
            Observable.from(array).map(new Func1<Integer, List<LatestNews.ListBean>>() {
                @Override
                public List<LatestNews.ListBean> call(Integer integer) {
                    List<SimpleNews> list = DataSupport.where("newsClassTag = ?", ""+integer).find(SimpleNews.class);
                    List<LatestNews.ListBean> newList = new ArrayList<>();
                    for (SimpleNews n : list)
                        newList.add(new LatestNews.ListBean().setNews_ID(n.getNews_ID())
                                                              .setNews_Title(n.getNews_Title())
//                                                              .setNews_Pictures(n.getNews_Pictures())
                                                                .setNews_Pictures("")
                                                                .setNews_Author(n.getNews_Author())
                                                                .setNews_Intro(n.getNews_Intro())
                                                                .setNews_Time(n.getNews_Time()));
                    return newList;
                }
            }).subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<List<LatestNews.ListBean>>() {
                   @Override
                   public void onCompleted() {
                       Log.d(TAG, "onCompleted(Not network): ");
                       if (newsList.size() > size_)
                           callback.result(newsList.subList(0, size_));
                       else
                           callback.result(newsList);
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.d(TAG, "onError(Not network): ");
                   }

                   @Override
                   public void onNext(List<LatestNews.ListBean> listBeen) {
                       Log.d(TAG, "onNext(Not network: ");
                       newsList.addAll(listBeen);
                   }
               });
        }
    }

//    @Override
//    public void getInitNews(final NewsContract.CallBackLatestNews callback, int size, int[] category) {
//        final int size_ = Math.min(size, MAX_SIZE);
//        List<Integer> array = new ArrayList<>();
//        for (int i : category)
//            array.add(i);
//        Observable.from(array).map(new Func1<Integer, List<LatestNews.ListBean>>() {
//            @Override
//            public List<LatestNews.ListBean> call(Integer integer) {
//                List<SimpleNews> list = DataSupport.where("newsClassTag = ?", ""+integer).find(SimpleNews.class);
//                List<LatestNews.ListBean> newList = new ArrayList<>();
//                for (SimpleNews n : list)
//                    newList.add(new LatestNews.ListBean().setNews_ID(n.getNews_ID())
//                            .setNews_Title(n.getNews_Title())
//                            .setNews_Pictures(n.getNews_Pictures()));
//                return newList;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<LatestNews.ListBean>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted(Not network): ");
//                        if (newsList.size() > size_)
//                            newsList = newsList.subList(0, size_);
//                        callback.result(newsList);
//                        // update
//                        for (LatestNews.ListBean bean : newsList)
//                            newsIdSet.add(bean.getNews_ID());
//                        newsList.addAll(currentList);
//                        currentList = newsList;
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError(Not network): ");
//                    }
//
//                    @Override
//                    public void onNext(List<LatestNews.ListBean> listBeen) {
//                        Log.d(TAG, "onNext(Not network: ");
//                        newsList.addAll(listBeen);
//                    }
//                });
//    }
//
//
//    @Override
//    public void getCurrentNews(NewsContract.CallBackLatestNews callback) {
//        callback.result(currentList);
//    }

    @Override
    public void getMoreNews(final NewsContract.CallBackLatestNews callback, final int size, int[] category) {
        final int size_ = Math.min(size, MAX_SIZE);
        if (TestNetwork.isNetworkAvailable()) {
            Subscriber subscriber = new Subscriber<LatestNews>() {

                @Override
                public void onCompleted() {

                    Log.d(TAG, "onCompleted: ");
                    List<LatestNews.ListBean> chooseList = newsList;
                    newsList = new ArrayList<>();
                    for (LatestNews.ListBean bean : chooseList)
                        if (! newsIdSet.contains(bean.getNews_ID()))
                            newsList.add(bean);
                    Collections.shuffle(newsList);
                    if (newsList.size() > size_)
                        newsList = newsList.subList(0, size_);
                    callback.result(newsList);
                    // update
                    for (LatestNews.ListBean bean : newsList)
                        newsIdSet.add(bean.getNews_ID());
                    newsList.addAll(currentList);
                    currentList = newsList;
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
            newsList = new ArrayList<>();
            if (pageNo > size_)
                NewsApi.getInstance().getLatestNews(subscriber, pageNo, pageNo, category);
            else
                NewsApi.getInstance().getLatestNews(subscriber, size_, pageNo, category);
            if (category.length == 1)
                pageNo ++;
        }
        else
            callback.result(new ArrayList<LatestNews.ListBean>());
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