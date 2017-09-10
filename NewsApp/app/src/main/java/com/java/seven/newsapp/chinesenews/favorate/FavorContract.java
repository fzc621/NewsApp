package com.java.seven.newsapp.chinesenews.favorate;

import com.java.seven.newsapp.bean.LatestNews;

import java.util.List;

/**
 * Created by zzy on 17-9-10.
 */

public interface FavorContract {

    interface  View{
        void refreshRecyclerVew(List<LatestNews.ListBean> list);
    }

    interface Presenter{
        void getFavor();
    }

    interface Model{
        void getFavor(CallBackFavor callback);
    }

    interface CallBackFavor {
        void result(List<LatestNews.ListBean> list);
    }
}
