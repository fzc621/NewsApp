package com.java.seven.newsapp.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zzy on 17-9-10.
 */

public class FavorNews extends DataSupport {
    private String news_id;
    public  String getNewsId() { return news_id; }
    public FavorNews setNewsId(String id) {
        this.news_id = id;
        return this;
    }
}
