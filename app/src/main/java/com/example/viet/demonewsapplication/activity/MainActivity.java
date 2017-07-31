package com.example.viet.demonewsapplication.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.viet.demonewsapplication.api.ApiApplication;
import com.example.viet.demonewsapplication.adapter.NewsHomeAdapter;
import com.example.viet.demonewsapplication.model.NewsItem;
import com.example.viet.demonewsapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "http://192.168.1.69/NewsApi/query.php/";
    private static final String ACTION_SELECT = "select";
    private static final String NUMBER_ROW = "10";
    protected ArrayList<NewsItem> arrNewsItem = new ArrayList<>();

    private NewsHomeAdapter newsHomeAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        newsHomeAdapter = new NewsHomeAdapter(arrNewsItem);
        recyclerView.setAdapter(newsHomeAdapter);
        selectRetrofit();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                selectRetrofit();
            }
        });

    }

    private void selectRetrofit() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(ApiApplication.class).select(ACTION_SELECT, NUMBER_ROW).enqueue(new Callback<ArrayList<NewsItem>>() {
            @Override
            public void onResponse(Call<ArrayList<NewsItem>> call, Response<ArrayList<NewsItem>> response) {
                Log.i(TAG, "onResponse");
                Toast.makeText(MainActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                ArrayList<NewsItem> arrTmp = response.body();
                if (arrTmp == null || arrTmp.isEmpty()) {
                    Toast.makeText(MainActivity.this, "...", Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                    return;
                }
                arrNewsItem.clear();
                arrNewsItem.addAll(response.body());
                newsHomeAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ArrayList<NewsItem>> call, Throwable t) {
                refreshLayout.setRefreshing(false);
            }
        });
    }

}
