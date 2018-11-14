package com.example.rkjc.news_app_2;

public class NewsItem {
    public String author;
    public String title;
    public String description;
    public String url;
    public String date;

    public NewsItem(String author, String title, String description,
                    String url, String date){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
