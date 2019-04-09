package com.paintboard.paintboard;

import com.vangogh.downloader.DocumentDownloader;
import com.vangogh.downloader.ImageDownloader;

import org.json.JSONArray;

public interface MainInteractor {
    interface MainInteractorListener {
        void onSuccess(JSONArray jsonResponse);
        void onFailed(String message);
    }

    void getHttpResponse(String url, DocumentDownloader docDownloader, ImageDownloader imgDownloader, MainInteractorListener listener);
}
