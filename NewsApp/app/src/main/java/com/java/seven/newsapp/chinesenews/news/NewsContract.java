package com.java.seven.newsapp.chinesenews.news;

import com.java.seven.newsapp.bean.LatestNews;

import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public interface NewsContract {

    interface View{
        void refreshRecyclerView(List<LatestNews.ListBean> storiesList);
    }

    interface Presenter{
        void getSearchNews(String keyword);
        void getLatestNews(int size, int[] category);
        void getOldNews(int size, int[] category);
    }

    interface Model{
        void getSearchNews(CallBackLatestNews callback, String keyword);
        void getLatestNews(CallBackLatestNews callback, int size, int[] category);
        void getOldNews(CallBackLatestNews callback, int size, int[] category);
    }

    interface CallBackLatestNews {
        void result(List<LatestNews.ListBean> list);
    }
}