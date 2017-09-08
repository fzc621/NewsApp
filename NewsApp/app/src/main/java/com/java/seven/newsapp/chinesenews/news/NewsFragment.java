package com.java.seven.newsapp.chinesenews.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.java.seven.newsapp.R;
import com.java.seven.newsapp.adapter.NewsSummaryAdapter;
import com.java.seven.newsapp.bean.LatestNews;

import java.util.List;

/**
 * Created by zzy on 17-9-8.
 */

public class NewsFragment extends Fragment implements NewsContract.View {

    private static final String TAG = "NewsFragment";
    RecyclerView recyclerView;
    private NewsContract.Presenter presenter;

    public NewsFragment() {
        presenter = new NewsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.latest_news_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        int[] category = {1};
        presenter.getLatestNews(20, category);

        return view;
    }

    @Override
    public void refreshRecyclerVew(List<LatestNews.ListBean> list) {
        Log.d(TAG, "refreshRecyclerVew: ");
        NewsSummaryAdapter adapter = new NewsSummaryAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}
