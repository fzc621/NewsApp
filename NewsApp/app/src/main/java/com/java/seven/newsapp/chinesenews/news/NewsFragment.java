package com.java.seven.newsapp.chinesenews.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.RefreshListAdapter;
import com.java.seven.newsapp.bean.LatestNews;

import com.java.seven.newsapp.widgets.RefreshListView;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsFragment extends Fragment implements NewsContract.View, RefreshListView.OnRefreshListener {

    private static final String TAG = "NewsFragment";
    RefreshListView refreshListView;
    private NewsContract.Presenter presenter;

    public NewsFragment() {
        presenter = new NewsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        refreshListView = (RefreshListView) view.findViewById(R.id.refresh_list);
        refreshListView.setOnRefreshListener(this);
        /*
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        refreshListView.setLayoutManager(layoutManager);*/
        if (categoryCode != 0) {
            int[] categoryCodes = {categoryCode};
            presenter.getLatestNews(20, categoryCodes);
        }
        else {
            int[] categoryCodes = NewsCategory.getCategoryCodesButAll();
            presenter.getLatestNews(20, categoryCodes);
        }

        return view;
    }

    @Override
    public void refreshRecyclerView(List<LatestNews.ListBean> list) {
        Log.d(TAG, "refreshRecyclerVew: ");
        RefreshListAdapter refreshListAdapter = (RefreshListAdapter)refreshListView.getAdapter();
        if (refreshListAdapter != null) {
            refreshListAdapter.onDateChange(list);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshListView.onRefreshComplete();
                }
            }, 1000);
        }
        else {
            refreshListAdapter = new RefreshListAdapter(getContext(), list);
            refreshListView.setAdapter(refreshListAdapter);
        }
    }


    private final int INC = 5;
    private int categoryCode;
    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
    public int getCategoryCode() {
        return categoryCode;
    }
    @Override
    public void onDownPullRefresh() {
        if (categoryCode != 0) {
            int[] categoryCodes = {categoryCode};
            presenter.getLatestNews(INC, categoryCodes);
        }
        else {
            int[] categoryCodes = NewsCategory.getCategoryCodesButAll();
            presenter.getLatestNews(INC, categoryCodes);
        }
    }

    @Override
    public void onLoadingMore() {
        // important
    }
}
