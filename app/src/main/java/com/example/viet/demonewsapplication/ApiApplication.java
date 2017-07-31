package com.example.viet.demonewsapplication;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by viet on 31/07/2017.
 */

public interface ApiApplication {
    @GET("http://192.168.1.69/NewsApi/querry.php")
    Call<ArrayList<NewsItem>> select(@Query("action") String action,@Query("num") String num );
}
