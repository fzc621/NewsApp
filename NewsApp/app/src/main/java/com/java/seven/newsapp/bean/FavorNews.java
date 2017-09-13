package com.java.seven.newsapp.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zzy on 17-9-10.
 */

public class FavorNews extends DataSupport {
    private String news_id;
    private String news_content;
    private String news_pictures;
    private String news_title;
    private String news_url;
    public  String getNewsId() { return news_id; }
    public FavorNews setNewsId(String id) {
        this.news_id = id;
        return this;
    }

    public String getNews_content() {
        return news_content;
    }

    public FavorNews setNews_content(String news_content) {
        this.news_content = news_content;
        return this;
    }

    public String getNews_pictures() {
        return news_pictures;
    }

    public FavorNews setNews_pictures(String news_pictures) {
        this.news_pictures = news_pictures;
        return this;
    }

    public String getNews_title() {
        return news_title;
    }

    public FavorNews setNews_title(String news_title) {
        this.news_title = news_title;
        return this;
    }

    public String getNews_url() {
        return news_url;
    }

    public FavorNews setNews_url(String news_url) {
        this.news_url = news_url;
        return this;
    }
}
