package com.paintboard.paintboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;

import com.paintboard.paintboard.models.ImageContent;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

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

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showContent(JSONArray jsonResponse) {

    }

    @Override
    public void showFailure() {
        new AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.failure_dialog_title))
                .setMessage(getString(R.string.failure_http_get_dialog_content))
                .setPositiveButton(getString(R.string.button_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}
