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
import com.java.seven.newsapp.activity.MainActivity;
import com.java.seven.newsapp.adapter.RefreshListAdapter;
import com.java.seven.newsapp.bean.LatestNews;

import com.java.seven.newsapp.util.AppGlobal;
import com.java.seven.newsapp.util.SevenFilter;
import com.java.seven.newsapp.widgets.RefreshListView;

import java.io.IOException;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsFragment extends Fragment
        implements NewsContract.View, RefreshListView.OnRefreshListener,
        MainActivity.OnIgnoreKeysChangeListener{

    private static final String TAG = "NewsFragment";
    View fragmentView;
    RefreshListView refreshListView;
    private NewsContract.Presenter presenter;
    private NewsFragment.OnIgnoreKeysChangeListener onIgnoreKeysChangeListener;

    public NewsFragment() {
        presenter = new NewsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_news, container, false);

        initViewRef();
        initView();
        initListener();

        if (categoryCode != 0) {
            int[] categoryCodes = {categoryCode};
            try {
                presenter.getInitNews(10, categoryCodes);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            presenter.getMoreNews(5, categoryCodes);
        } else {
            int[] categoryCodes = NewsCategory.getCategoryCodesButAll();
            try {
                presenter.getInitNews(10, categoryCodes);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            presenter.getMoreNews(5, categoryCodes);
        }

        return fragmentView;
    }

    private void initViewRef() {
        refreshListView = fragmentView.findViewById(R.id.refresh_list);
    }

    private void initView() {

    }

    private void initListener() {
        refreshListView.setOnRefreshListener(this);
    }

    @Override
    public void refreshRecyclerVew(List<LatestNews.ListBean> list) {
        Log.d(TAG, "refreshRecyclerVew: ");
        RefreshListAdapter refreshListAdapter = (RefreshListAdapter)refreshListView.getAdapter();
        list = SevenFilter.filter(list, AppGlobal.ignoreKeys);
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
        onIgnoreKeysChangeListener = refreshListAdapter;
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
            presenter.getMoreNews(INC, categoryCodes);
        }
        else {
            int[] categoryCodes = NewsCategory.getCategoryCodesButAll();
            presenter.getMoreNews(INC, categoryCodes);
        }
    }

    @Override
    public void onLoadingMore() {
        // important
    }

    @Override
    public void onIgnoreKeysChange(String[] ignoreKeys) {
        if (onIgnoreKeysChangeListener != null) {
            onIgnoreKeysChangeListener.onIgnoreKeysChange(ignoreKeys);
        }
    }

    public interface OnIgnoreKeysChangeListener {
        public void onIgnoreKeysChange(String[] ignoreKeys);
    }
}
