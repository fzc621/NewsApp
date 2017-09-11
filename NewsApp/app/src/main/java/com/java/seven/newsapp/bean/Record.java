package com.java.seven.newsapp.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zzy on 2017/9/11.
 */

public class Record extends DataSupport {
    private String news_id;

    public String getNews_id() { return news_id; }

    public Record setNews_id(String news_id) {
        this.news_id = news_id;
        return this;
    }
}
