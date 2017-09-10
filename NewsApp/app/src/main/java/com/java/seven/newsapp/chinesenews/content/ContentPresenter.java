package com.java.seven.newsapp.chinesenews.content;

import com.java.seven.newsapp.bean.News;

/**
 * Created by linsawako on 2017/1/30.
 */

public class ContentPresenter implements ContentContract.Presenter {

    private ContentContract.View view;
    private ContentContract.Model model;

    public ContentPresenter(ContentContract.View view) {
        this.view = view;
        model = new ContentModel();
    }

    @Override
    public void getNews(String id) {
        model.getNews(new CallBackNewsContent() {
            @Override
            public void result(News news) {
                view.setContent(news.getNews_Content());
                view.setTitleImage(news.getNews_Pictures());
                view.setTitle(news.getNews_Title());
                view.setUrl(news.getNews_URL());
            }
        }, id);
    }

}
