package com.java.seven.newsapp.chinesenews.favorate;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.RefreshListAdapter;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.widgets.RefreshListView;

import java.util.ArrayList;
import java.util.List;

public class FavorActivity extends AppCompatActivity implements FavorContract.View, RefreshListView.OnRefreshListener{

    private static final String TAG = "FavorActivity";
    private FavorContract.Presenter presenter;
    private Toolbar toolbar;
    private RefreshListView refreshListView;

    public FavorActivity() { presenter = new FavorPresenter(this); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor);
        refreshListView = (RefreshListView) findViewById(R.id.refresh_list);
        refreshListView.setOnRefreshListener(this);
        presenter.getFavor();
    }

    @Override
    public void refreshRecyclerVew(List<LatestNews.ListBean> list) {
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
            refreshListAdapter = new RefreshListAdapter(this, list);
            refreshListView.setAdapter(refreshListAdapter);
        }
    }

    @Override
    public void onDownPullRefresh() {
        presenter.getFavor();
    }

    @Override
    public void onLoadingMore() {

    }
}
