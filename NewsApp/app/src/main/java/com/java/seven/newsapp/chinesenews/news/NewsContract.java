package com.java.seven.newsapp.chinesenews.news;

import com.java.seven.newsapp.bean.LatestNews;

import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public interface NewsContract {

    interface View{
        void refreshRecyclerVew(List<LatestNews.ListBean> storiesList);
    }

    interface Presenter{
        void getSearchNews(String keyword);
//        void getInitNews(int size, int[] category); // init
//        void getCurrentNews(); // no change, just for light/night mode etc.
        void getMoreNews(int size, int[] category); // add more news to current news
        void getLatestNews(int size, int[] category);
    }

    interface Model{
        void getSearchNews(CallBackLatestNews callback, String keyword);
        void getLatestNews(CallBackLatestNews callback, int size, int[] category);
//        void getInitNews(CallBackLatestNews callback, int size, int[] category); // init
//        void getCurrentNews(CallBackLatestNews callback); // no change, just for light/night mode etc.
        void getMoreNews(CallBackLatestNews callback, int size, int[] category); // add more news to current news
    }

    interface CallBackLatestNews {
        void result(List<LatestNews.ListBean> list);
    }
}