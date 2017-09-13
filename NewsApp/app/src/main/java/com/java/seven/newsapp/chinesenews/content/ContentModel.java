package com.java.seven.newsapp.chinesenews.content;

import com.java.seven.newsapp.api.NewsApi;
import com.java.seven.newsapp.bean.FavorNews;
import com.java.seven.newsapp.bean.News;
import com.mob.tools.utils.Data;

import org.litepal.crud.DataSupport;

import rx.Subscriber;

/**
 * Created by linsawako on 2017/1/30.
 */

public class ContentModel implements ContentContract.Model {

    @Override
    public void getNews(final CallBackNewsContent callback, String id) {
        FavorNews news = DataSupport.where("news_id = ?", id).findFirst(FavorNews.class);
        if (news == null) {
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
        else {
            callback.result(new News().setNews_Content(news.getNews_content())
            .setNews_ID(news.getNewsId())
            .setNews_Pictures(news.getNews_pictures())
            .setNews_URL(news.getNews_url())
            .setNews_Title(news.getNews_title()));
        }
    }

}
