package com.java.seven.newsapp.chinesenews.news;

import android.util.Log;

import com.java.seven.newsapp.bean.LatestNews;

import java.io.IOException;
import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsPresenter implements NewsContract.Presenter {

    private static final String TAG = "NewsPresenter";
    private NewsContract.View view;
    private NewsContract.Model model;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
        model = new NewsModel();
    }

    @Override
    public void getLatestNews(int size, int[] category) {
        Log.d(TAG, "getLatestNews");
        model.getLatestNews(new NewsContract.CallBackLatestNews() {
            @Override
            public void result(List<LatestNews.ListBean> list) {
                view.refreshRecyclerView(list);
            }
        },
        size, category);
    }

    @Override
    public void getInitNews(int size, int[] category) throws IOException, ClassNotFoundException {
        Log.d(TAG, "getInitNews");
        model.getInitNews(new NewsContract.CallBackLatestNews() {
                                @Override
                                public void result(List<LatestNews.ListBean> list) {
                                    view.refreshRecyclerView(list);
                                }
        }, size, category);
    }

    @Override
    public void getSearchNews(String keyword) {
        Log.d(TAG, "getSearchNews");
        model.getSearchNews(new NewsContract.CallBackLatestNews() {
            @Override
            public void result(List<LatestNews.ListBean> list) {
                view.refreshRecyclerView(list);
            }
        }, keyword);
    }

//    @Override
//    public void getCurrentNews() {
//        Log.d(TAG, "getCurrentNews");
//        model.getCurrentNews(new NewsContract.CallBackLatestNews() {
//                                @Override
//                                public void result(List<LatestNews.ListBean> list) {
//                                    view.refreshRecyclerVew(list);
//                                }
//                            });
//    }

    @Override
    public void getMoreNews(int size, int[] category) {
        Log.d(TAG, "getMoreNews");
        model.getMoreNews(new NewsContract.CallBackLatestNews() {
            @Override
            public void result(List<LatestNews.ListBean> list) {
                view.refreshRecyclerView(list);
            }
        }, size, category);
    }

}
