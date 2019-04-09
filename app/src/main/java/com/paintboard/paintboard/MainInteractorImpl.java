package com.paintboard.paintboard;

import android.util.Log;

import com.vangogh.downloader.DocumentDownloader;
import com.vangogh.downloader.ImageDownloader;

import org.json.JSONArray;
import org.json.JSONException;

public class MainInteractorImpl implements MainInteractor {
    @Override
    public void getHttpResponse(String url, DocumentDownloader docDownloader, ImageDownloader imgDownloader, final MainInteractorListener listener) {
        /**
         * TODO :
         * Call Vangogh document downloader here
         */
        docDownloader.toJSON(url, new DocumentDownloader.DocumentResponse<JSONArray>() {
            @Override
            public void onStart() {
                Log.d(DocumentDownloader.class.getSimpleName(), "*** Start Downloading ... ***");
            }

            @Override
            public void onSuccess(String raw, JSONArray response) {
                Log.d(DocumentDownloader.class.getSimpleName(), raw);

                try {
                    Log.d(DocumentDownloader.class.getSimpleName(), response.get(0).toString());

                    listener.onSuccess(response);
                } catch (JSONException e) {
                    Log.d(DocumentDownloader.class.getSimpleName(), e.getMessage());
                    listener.onFailed(e.getMessage());
                }
            }

            @Override
            public void onFailed(Exception e) {
                listener.onFailed(e.getMessage());
            }
        });
    }
}
