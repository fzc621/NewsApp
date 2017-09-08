package com.java.seven.newsapp.api;

/**
 * Created by zzy on 17-9-8.
 */

import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.bean.News;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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

    public void getLatestNews(Subscriber<LatestNews> subscriber){
        tsinghuaService.getLatestNews()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getSearchNews(Subscriber<LatestNews> subscriber, String keyWord){
        tsinghuaService.getSearchNews(keyWord)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public  void getDetailNews(Subscriber<News> subscriber, String newsId) {
        tsinghuaService.getDetailNews(newsId)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
