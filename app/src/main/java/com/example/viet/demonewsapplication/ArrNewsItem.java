package com.example.viet.demonewsapplication;

import java.util.ArrayList;

/**
 * Created by viet on 31/07/2017.
 */

public class ArrNewsItem {
    ArrayList<NewsItem> arrNewsItem;

    public ArrNewsItem(ArrayList<NewsItem> arrNewsItem) {
        this.arrNewsItem = arrNewsItem;
    }

    public ArrayList<NewsItem> getArrNewsItem() {
        return arrNewsItem;
    }

    public void setArrNewsItem(ArrayList<NewsItem> arrNewsItem) {
        this.arrNewsItem = arrNewsItem;
    }
}
