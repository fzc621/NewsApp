package com.java.seven.newsapp.chinesenews.content;

import com.java.seven.newsapp.api.NewsApi;
import com.java.seven.newsapp.bean.News;
import com.java.seven.newsapp.bean.StoryExtra;

import rx.Subscriber;

/**
 * Created by linsawako on 2017/1/30.
 */

public class ContentModel implements ContentContract.Model {

    @Override
    public void getNews(final CallBackNewsContent callback, String id) {
        Subscriber subscriber = new Subscriber<News>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(News news) {
                callback.result(news);
            }
        };
        NewsApi.getInstance().getDetailNews(subscriber, id);
    }

}
