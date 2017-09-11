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
    private String news_Intro;
    private String news_Author;
    private String news_Time;


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

    public String getNews_Author() { return news_Author; }

    public SimpleNews setNews_Author(String news_Author) {
        this.news_Author = news_Author;
        return this;
    }

    public String getNews_Time() {
        return news_Time;
    }

    public SimpleNews setNews_Time(String news_Time) {
        this.news_Time = news_Time;
        return this;
    }

    public String getNews_Intro() {
        return news_Intro;
    }

    public SimpleNews setNews_Intro(String news_Intro) {
        this.news_Intro = news_Intro;
        return this;
    }
}
