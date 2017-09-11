package com.java.seven.newsapp.api;

/**
 * Created by zzy on 17-9-8.
 */

import android.util.Log;

import com.google.android.gms.common.data.DataBufferObserver;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.News;
import com.java.seven.newsapp.bean.Record;
import com.java.seven.newsapp.bean.SimpleNews;
import com.java.seven.newsapp.chinesenews.news.NewsCategory;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class NewsApi {
    private static final int DEFAULT_TIMEOUT = 5;
    private NewsServices tsinghuaService;
    private static NewsApi tsinghuaApi;
    private Retrofit retrofit;

    private NewsApi() {
        //设置超时时间
        OkHttpClient.Builder httpcientBuilder = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpcientBuilder.build())//
                .baseUrl(Config.NEWS_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tsinghuaService = retrofit.create(NewsServices.class);
    }

    public static NewsApi getInstance(){
        if (tsinghuaApi == null) {
            synchronized (NewsApi.class){
                if (tsinghuaApi == null){
                    tsinghuaApi = new NewsApi();
                }
            }
        }
        return tsinghuaApi;
    }

    public void getLatestNews(Subscriber<LatestNews> subscriber, int size, int[] category){
        List<Observable<LatestNews>> array = new ArrayList<>();
        if (category.length == 1)
            size *= 2;
        for (int i : category) {
            array.add(tsinghuaService.getLatestNews(i, size)
                            .subscribeOn(Schedulers.io())
                            .unsubscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()));
        }
        Observable<LatestNews> merge = Observable.merge(array).map(new Func1<LatestNews, LatestNews>() {
            @Override
            public LatestNews call(LatestNews latestNews) {
                for (LatestNews.ListBean bean : latestNews.getList()) {
                    new SimpleNews().setNewsClassTag(NewsCategory.nameToCode(bean.getNewsClassTag()))
                                    .setNews_ID(bean.getNews_ID())
                                    .setNews_Pictures(bean.getNews_Pictures())
                                    .setNews_Title(bean.getNews_Title())
                                    .setNews_Intro(bean.getNews_Intro())
                                    .setNews_Author(bean.getNews_Author())
                                    .setNews_Time(bean.getNews_Time()).save();
                    if (DataSupport.where("news_id = ?", bean.getNews_ID()).findFirst(Record.class) != null) {
                        bean.setRead(true);
                    }
                }
                return latestNews;
            }

        });
        merge.subscribe(subscriber);
    }

    public void getSearchNews(Subscriber<LatestNews> subscriber, String keyWord){
        tsinghuaService.getSearchNews(keyWord)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getDetailNews(Subscriber<News> subscriber, String newsId) {
        tsinghuaService.getDetailNews(newsId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getNews(Subscriber<LatestNews.ListBean> subscriber, String[] ids) {
        List<Observable<News>> array = new ArrayList<>();
        for (String id : ids) {
            array.add(tsinghuaService.getDetailNews(id)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()));
        }
        Observable<LatestNews.ListBean> merrge = Observable.merge(array).map(new Func1<News, LatestNews.ListBean>() {
            @Override
            public LatestNews.ListBean call(News news) {
                return new LatestNews.ListBean().setNews_ID(news.getNews_ID())
                        .setNews_Pictures(news.getNews_Pictures())
                        .setNews_Title(news.getNews_Title())
                        .setNews_Author(news.getNews_Author())
                        .setNews_Intro(news.getNews_Content().substring(0, 20))
                        .setNews_Time(news.getNews_Time());
            }
        });
        merrge.subscribe(subscriber);
    }
}
