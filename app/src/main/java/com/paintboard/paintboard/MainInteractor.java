package com.paintboard.paintboard;

import org.json.JSONArray;

public interface MainInteractor {
    interface MainInteractorListener {
        void onSuccess(JSONArray jsonResponse);
        void onFailed();
    }

    void getHttpResponse(String url, MainInteractorListener listener);
}
