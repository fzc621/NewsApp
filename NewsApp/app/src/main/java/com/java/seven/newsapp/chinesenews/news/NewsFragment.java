package com.java.seven.newsapp.chinesenews.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.RefreshListAdapter;
import com.java.seven.newsapp.bean.LatestNews;

import com.java.seven.newsapp.widgets.RefreshListView;
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
    public void refreshRecyclerVew(List<LatestNews.ListBean> list) {
        Log.d(TAG, "refreshRecyclerVew: ");
        RefreshListAdapter adapter = new RefreshListAdapter(getContext(), list);
        refreshListView.setAdapter(adapter);
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
        /*
        Presenter presenter = new Presenter();
        presenter.getLatestNews(INC, category);
         */
    }

    @Override
    public void onLoadingMore() {
        // important
    }
}
