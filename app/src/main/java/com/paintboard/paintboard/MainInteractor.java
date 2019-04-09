package com.paintboard.paintboard;

public interface MainInteractor {
    interface MainInteractorListener {
        void onSuccess();
        void onFailed();
    }

    void getHttpResponse(String url, MainInteractorListener listener);
}
