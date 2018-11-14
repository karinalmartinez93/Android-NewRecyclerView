package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder>{

    Context context;
    ArrayList<NewsItem> allArticles;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> allArticles){
        this.context = context;
        this.allArticles = allArticles;
    }

    @Override
    public int getItemCount(){
        return allArticles.size();
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView articleTitle;
        TextView articleDescription;
        TextView articleDate;

        public NewsItemViewHolder(View view){
            super(view);
            articleTitle = (TextView) view.findViewById(R.id.article_title);
            articleDescription = (TextView) view.findViewById(R.id.article_description);
            articleDate = (TextView) view.findViewById(R.id.article_date);
        }

        public void bind(final int location){
            NewsItem currentArticle = allArticles.get(location);
            articleTitle.setText("Title: " + currentArticle.getTitle());
            articleDescription.setText("Description: " + currentArticle.getDescription());
            articleDate.setText("Date: " + currentArticle.getDate());

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String articleUrlString = allArticles.get(location).getUrl();
                    Uri articleWebpage = Uri.parse(articleUrlString);
                    Intent intent = new Intent(Intent.ACTION_VIEW, articleWebpage, context, Webpage.class);
                    intent.putExtra("urlString", articleUrlString);
                    context.startActivity(intent);
                }
            });
        }
    }

}
