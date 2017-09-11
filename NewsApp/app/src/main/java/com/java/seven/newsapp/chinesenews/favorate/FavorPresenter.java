package com.java.seven.newsapp.chinesenews.favorate;

import android.util.Log;

import com.java.seven.newsapp.bean.LatestNews;

import java.util.List;

/**
 * Created by zzy on 17-9-10.
 */

public class FavorPresenter implements FavorContract.Presenter {

    private static final String TAG = "FavorPresenter";
    private FavorContract.View view;
    private FavorContract.Model model;

    public FavorPresenter(FavorContract.View view) {
        this.view = view;
        model = new FavorModel();
    }

    @Override
    public void getFavor() {
        Log.d(TAG, "getFavor: ");
        model.getFavor(new FavorContract.CallBackFavor() {
            @Override
            public void result(List<LatestNews.ListBean> list) {
                view.refreshRecyclerVew(list);
            }
        });
    }
}
