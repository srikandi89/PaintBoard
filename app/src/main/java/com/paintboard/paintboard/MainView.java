package com.paintboard.paintboard;

import org.json.JSONArray;

public interface MainView {
    void showLoading();
    void hideLoading();
    void showFailure();
    void showContent(JSONArray jsonResponse);
}
