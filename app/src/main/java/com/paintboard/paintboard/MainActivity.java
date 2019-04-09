package com.paintboard.paintboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.paintboard.paintboard.models.ImageContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvItems;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ImageContent> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void init() {
        rvItems = findViewById(R.id.rv_items);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        contents = new ArrayList<>();
    }

    @Override
    public void onRefresh() {
        /**
         * Do http request here...
         */
    }
}
