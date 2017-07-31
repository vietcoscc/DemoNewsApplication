package com.example.viet.demonewsapplication.api;

import com.example.viet.demonewsapplication.model.NewsItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by viet on 31/07/2017.
 */

public interface ApiApplication {
    @GET("http://192.168.1.69/NewsApi/query.php")
    Call<ArrayList<NewsItem>> select(@Query("action") String action, @Query("num") String num );
}
