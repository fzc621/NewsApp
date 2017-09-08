package com.java.seven.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.java.seven.newsapp.R;
import com.java.seven.newsapp.bean.LatestNews;
import com.java.seven.newsapp.chinesenews.content.ContentActivity;

import java.util.List;

/**
 * Map a position with to a view
 * position -> LatestNews.ListBean[i] -> view(a news item)
 */
public class RefreshListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<LatestNews.ListBean> items;
    public static final String NEWS_ID = "news _id";

    public RefreshListAdapter(Context context, List<LatestNews.ListBean> list) {
        this.context = context;
        this.items = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final int positionRef = position;
        ViewHolder viewHolderRef;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.latest_news_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LatestNews.ListBean storiesBean = items.get(positionRef);
                    String id = storiesBean.getNews_ID();
                    Intent intent = new Intent(context, ContentActivity.class);
                    intent.putExtra(NEWS_ID, id);
                    context.startActivity(intent);
                }
            });
            viewHolderRef = viewHolder;

        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolderRef = viewHolder;
        }

        LatestNews.ListBean storiesBean = items.get(position);
        viewHolderRef.newsText.setText(storiesBean.getNews_Title());
        Glide.with(context).load(storiesBean.getNews_Pictures()).into(viewHolderRef.newsImage);

        return convertView;
    }


    /**
     * After refreshing, the news list in refreshed.
     * It should be synced.
     * @param storiesList
     */
    public void onDateChange(List<LatestNews.ListBean> storiesList) {
        this.items = storiesList;
        this.notifyDataSetChanged();
    }

    class ViewHolder {
        CardView cardView;
        ImageView newsImage;
        TextView newsText;

        public ViewHolder(View itemView) {
            cardView = itemView.findViewById(R.id.latest_news_cardview);
            newsImage = itemView.findViewById(R.id.latest_news_image);
            newsText = itemView.findViewById(R.id.latest_news_title);
        }
    }

}
