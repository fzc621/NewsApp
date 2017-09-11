package com.java.seven.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.activity.MainActivity;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.chinesenews.content.ContentActivity;
import com.java.seven.newsapp.util.AppGlobal;
import com.java.seven.newsapp.util.SevenPreprocessor;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * Map a position with to a view
 * position -> LatestNews.ListBean[i] -> view(a news item)
 */
public class RefreshListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;

    private LinkedList<LatestNews.ListBean> items;
    private LinkedList<View> views;

    public static final String NEWS_ID = "news _id";

    public RefreshListAdapter(Context context, List<LatestNews.ListBean> list) {
        this.context = context;
        this.items = new LinkedList<>(list);
        this.views = new LinkedList<>();
        for (int i = 0; i < items.size(); ++i)
            this.views.add(null);
        inflater = LayoutInflater.from(context);
    }

    public List<LatestNews.ListBean> getItems() {
        return items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("RefreshListView.getView", String.valueOf(position));
        if (views.get(position) != null) {
            return views.get(position);
        }
        else {
            LatestNews.ListBean storiesBean = items.get(position);

            String[] temp = storiesBean.getNews_Pictures().trim().split(" ");
            ArrayList<String> filtered = new ArrayList<>();
            for (int i = 0; i < temp.length; ++i) {
                if (!temp[i].equals(""))
                    filtered.add(temp[i]);
            }
            String[] urls = new String[filtered.size()];
            for (int i = 0; i < urls.length; ++i) {
                urls[i] = filtered.get(i);
            }

            int numUrls = urls.length;
            int numImgs = Math.max(0, Math.min(numUrls, 3));
            if (AppGlobal.saveDataUsage == true)
                numImgs = 0;

            int layoutId = -1;
            switch (numImgs) {
                case 0: layoutId = R.layout.news_item_0; break;
                case 1: layoutId = R.layout.news_item_1; break;
                case 2: layoutId = R.layout.news_item_2; break;
                case 3: layoutId = R.layout.news_item_3; break;
                default: break;
            }

            View view = inflater.inflate(layoutId, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LatestNews.ListBean storiesBean = items.get(position);
                    String id = storiesBean.getNews_ID();
                    Intent intent = new Intent(context, ContentActivity.class);
                    intent.putExtra(NEWS_ID, id);
                    context.startActivity(intent);
                    setReadColor(view.getContext(), viewHolder);
                }
            });

            viewHolder.newsTitle.setText(storiesBean.getNews_Title());
            viewHolder.newsIntro.setText(SevenPreprocessor.preprocessIntro(storiesBean.getNews_Intro()));
            String author = SevenPreprocessor.preprocessAuthor(storiesBean.getNews_Author());
            String time = SevenPreprocessor.preprocessTime(storiesBean.getNews_Time());
            String foot = author + "  " + time;
            viewHolder.newsFoot.setText(foot);

            LinearLayout imageContainer = viewHolder.imageContainer;
            for (int i = 0; i < numImgs; ++i) {
                Glide.with(context)
                        .load(urls[i])
                        .centerCrop()
                        .error(R.drawable.error_404)
                        .into((ImageView)imageContainer.getChildAt(i));
            }

            if(storiesBean.getRead()) {
                setReadColor(parent.getContext(), viewHolder);
            }

            return view;
        }

    }

    /**
     * After refreshing, the news list in refreshed.
     * It should be synced.
     * @param storiesList
     */
    public void onDateChange(List<LatestNews.ListBean> storiesList) {
        items.addAll(0, storiesList);
        for (int i = 0; i < storiesList.size(); ++i) {
            views.addFirst(null);
        }
        this.notifyDataSetChanged();
    }

    public void onDataChangeForFavor(List<LatestNews.ListBean> storiesList) {
        items = new LinkedList<LatestNews.ListBean>(storiesList);
        views = new LinkedList<>();
        for (int i = 0; i < storiesList.size(); ++i) {
            views.addFirst(null);
        }
        this.notifyDataSetChanged();
    }

    class ViewHolder {
        CardView cardView;
        TextView newsTitle;
        TextView newsIntro;
        LinearLayout imageContainer;
        TextView newsFoot;
        public ViewHolder(View itemView) {
            cardView = itemView.findViewById(R.id.latest_news_cardview);
            newsTitle = itemView.findViewById(R.id.latest_news_title);
            newsIntro = itemView.findViewById(R.id.latest_news_intro);
            imageContainer = itemView.findViewById(R.id.image_container);
            newsFoot = itemView.findViewById(R.id.latest_news_foot);
        }
    }

    public void setReadColor(Context context, ViewHolder viewHolder){
        viewHolder.newsTitle.setTextColor(ContextCompat.getColor(context, R.color.readNewsTitle));
        viewHolder.newsIntro.setTextColor(ContextCompat.getColor(context, R.color.readNewsIntro));
    }
}
