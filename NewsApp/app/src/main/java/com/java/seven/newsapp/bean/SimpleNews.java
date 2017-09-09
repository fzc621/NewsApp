package com.java.seven.newsapp.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zzy on 17-9-9.
 */

public class SimpleNews extends DataSupport{
    private int newsClassTag;
    private String news_ID;
    private String news_Pictures;
    private String news_Title;


    public int getNewsClassTag() {
        return newsClassTag;
    }

    public SimpleNews setNewsClassTag(int newsClassTag) {
        this.newsClassTag = newsClassTag;
        return this;
    }


    public String getNews_ID() {
        return news_ID;
    }

    public SimpleNews setNews_ID(String news_ID) {
        this.news_ID = news_ID;
        return this;
    }

    public String getNews_Pictures() {
        return news_Pictures;
    }

    public SimpleNews setNews_Pictures(String news_Pictures) {
        this.news_Pictures = news_Pictures;
        return this;
    }


    public String getNews_Title() {
        return news_Title;
    }

    public SimpleNews setNews_Title(String news_Title) {
        this.news_Title = news_Title;
        return this;
    }

}
