package com.java.seven.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.chinesenews.content.ContentActivity;

import java.util.List;

/**
 * Created by linsawako on 2017/1/21.
 */

public class NewsSummaryAdapter extends RecyclerView.Adapter<NewsSummaryAdapter.ViewHolder> {

    private Context mContext;
    private List<LatestNews.ListBean> storiesList;
    public static final String NEWS_ID = "news _id";

    class ViewHolder extends  RecyclerView.ViewHolder{

        CardView cardView;
        ImageView newsImage;
        TextView newsText;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.latest_news_cardview);
            newsImage = (ImageView) itemView.findViewById(R.id.latest_news_image);
            newsText = (TextView) itemView.findViewById(R.id.latest_news_title);
        }
    }

    public NewsSummaryAdapter(List<LatestNews.ListBean> list){
        this.storiesList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.latest_news_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                LatestNews.ListBean storiesBean = storiesList.get(position);
//                int id = storiesBean.getId();
                String id = storiesBean.getNews_ID();
                Intent intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra(NEWS_ID, id);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LatestNews.ListBean storiesBean = storiesList.get(position);
        holder.newsText.setText(storiesBean.getNews_Title());
        Glide.with(mContext).load(storiesBean.getNews_Pictures()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

}
