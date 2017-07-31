package com.example.viet.demonewsapplication;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    protected ArrayList<NewsItem> arrNewsItem = new ArrayList<>();
    private NewsHomeAdapter newsHomeAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        newsHomeAdapter = new NewsHomeAdapter(arrNewsItem);
        recyclerView.setAdapter(newsHomeAdapter);
        selectRetrofit();
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                selectRetrofit();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void selectRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.69/NewsApi/query.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(ApiApplication.class).select("select", "10").enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                Log.i(TAG, "onResponse");
//                ArrayList<NewsItem> arrTmp = response.body();
                arrNewsItem.clear();
                arrNewsItem.addAll(response.body());
                newsHomeAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "News", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {

            }
        });
    }
}
