package com.example.viet.demonewsapplication.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.viet.demonewsapplication.R;
import com.example.viet.demonewsapplication.model.NewsItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vaio on 1/26/2017.
 */

public class NewsHomeAdapter extends RecyclerView.Adapter<NewsHomeAdapter.ViewHolder> {

    private ArrayList<NewsItem> arrNewsItem;
    private Context context;

    public interface ClickListener {
        void onItemClick(View view, int position);
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public NewsHomeAdapter(ArrayList<NewsItem> arrNewsItem) {
        this.arrNewsItem = arrNewsItem;

    }

    @Override
    public NewsHomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_news_recycler_view, parent, false);
        return new NewsHomeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final NewsItem newsItem = arrNewsItem.get(position);
        if (newsItem != null) {
            Glide.with(context).load(newsItem.getImageLinkWrapper()).into(holder.ivWrapper);

            holder.tvName.setText(newsItem.getName());
            holder.tvContentPreview.setText(newsItem.getContentPreview());
            holder.tvTopicName.setText(newsItem.getTopicName());
            holder.tvAuthor.setText(newsItem.getAuthor());
            holder.tvTimeStamp.setText(newsItem.getTimeStamp());
            holder.ivWatchLater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {

        }
        if (position >= arrNewsItem.size() - 1) {
            if (onCompleteLoading != null) {
                onCompleteLoading.onSuccess();
            }
            return;
        }
    }

    @Override
    public int getItemCount() {
        return arrNewsItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.ivWrapper)
        ImageView ivWrapper;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvContentPreview)
        TextView tvContentPreview;
        @BindView(R.id.tvTopicName)
        TextView tvTopicName;
        @BindView(R.id.tvAuthor)
        TextView tvAuthor;
        @BindView(R.id.tvTimeStamp)
        TextView tvTimeStamp;
        @BindView(R.id.watchLater)
        ImageView ivWatchLater;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(v, getPosition());
            }
        }

    }

    public void setOnCompleteLoading(OnCompleteLoading onCompleteLoading) {
        this.onCompleteLoading = onCompleteLoading;
    }

    private OnCompleteLoading onCompleteLoading;

    public interface OnCompleteLoading {
        void onSuccess();
    }
}
