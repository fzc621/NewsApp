package com.java.seven.newsapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.vision.text.Line;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.RefreshListAdapter;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.chinesenews.news.NewsCategory;
import com.java.seven.newsapp.chinesenews.news.NewsContract;
import com.java.seven.newsapp.chinesenews.news.NewsPresenter;
import com.java.seven.newsapp.widgets.RefreshListView;

import java.util.List;

/**
 * Created by caibao on 17-9-11.
 */

public class SearchActivity extends AppCompatActivity implements NewsContract.View, SearchView.OnQueryTextListener {

    private static final String TAG = "NewsFragment";
    ListView searchListView;
    SearchView searchView;
    LinearLayout searchHint;
    LinearLayout searchFail;
    private NewsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViewRef();
        initView();
        initListener();

        presenter = new NewsPresenter(this);

    }

    private void initViewRef() {
        searchView = (SearchView)findViewById(R.id.news_search);
        searchListView = (ListView)findViewById(R.id.search_list);
        searchHint = (LinearLayout)findViewById(R.id.search_hint);
        searchFail = (LinearLayout)findViewById(R.id.search_fail);
    }

    private void initView() {
        searchListView.setVisibility(View.GONE);
        searchHint.setVisibility(View.VISIBLE);
        searchFail.setVisibility(View.GONE);
    }

    private void initListener() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void refreshRecyclerView(List<LatestNews.ListBean> list) {
        Log.d(TAG, "refreshRecyclerVew: ");
        RefreshListAdapter refreshListAdapter = new RefreshListAdapter(this, list);
        searchListView.setAdapter(refreshListAdapter);
        if (list.size() == 0) {
            searchListView.setVisibility(View.GONE);
            searchHint.setVisibility(View.GONE);
            searchFail.setVisibility(View.VISIBLE);
        }
        else {
            searchHint.setVisibility(View.GONE);
            searchFail.setVisibility(View.GONE);
            searchListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.getSearchNews(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
