package com.paintboard.paintboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.paintboard.paintboard.adapters.ImageListAdapter;
import com.paintboard.paintboard.models.ImageContent;
import com.vangogh.downloader.DocumentDownloader;
import com.vangogh.downloader.ImageDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rvItems;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ImageContent> contents;
    private DocumentDownloader documentDownloader;
    private ImageDownloader imageDownloader;
    private MainPresenter presenter;
    private String dataUrl;
    private ImageListAdapter adapter;

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
        documentDownloader = ((MyApplication)this.getApplication()).getDocumentDownloader();
        imageDownloader = ((MyApplication)this.getApplication()).getImageDownloader();
        presenter = new MainPresenterImpl(this);
        dataUrl = getString(R.string.data_url);

        Log.d(MainActivity.class.getSimpleName(), "Document Downloader == Null ? "+(documentDownloader == null));
        Log.d(MainActivity.class.getSimpleName(), "Image Downloader == Null ? "+(imageDownloader == null));
    }

    @Override
    public void onRefresh() {
        /**
         * Do http request here...
         */
        presenter.doGetHttpResponse(dataUrl, documentDownloader, imageDownloader);
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
        Log.d(MainActivity.class.getSimpleName(), jsonResponse.toString());

        try {
            for (int i=0; i<jsonResponse.length(); i++) {
                JSONObject jsonObject = jsonResponse.getJSONObject(i);

                JSONObject rawImageUrl = jsonObject.getJSONObject("urls");
                String url = rawImageUrl.getString("raw");
                contents.add(new ImageContent(url));
            }

            if (contents.size() > 0) {
                adapter = new ImageListAdapter(this, contents, imageDownloader);
                GridLayoutManager llm = new GridLayoutManager(getApplicationContext(), 2);
                llm.setOrientation(RecyclerView.VERTICAL);
                rvItems.setLayoutManager(llm);
                rvItems.setItemAnimator(new DefaultItemAnimator());
                rvItems.setAdapter(adapter);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
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
