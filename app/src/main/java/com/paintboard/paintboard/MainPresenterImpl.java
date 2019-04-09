package com.paintboard.paintboard;

import com.vangogh.downloader.DocumentDownloader;
import com.vangogh.downloader.ImageDownloader;

import org.json.JSONArray;

public class MainPresenterImpl implements MainPresenter, MainInteractor.MainInteractorListener {
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenterImpl(MainView mView) {
        this.mView = mView;
        mInteractor = new MainInteractorImpl();
    }

    @Override
    public void onSuccess(JSONArray jsonResponse) {
        mView.hideLoading();
        mView.showContent(jsonResponse);
    }

    @Override
    public void onFailed(String message) {
        mView.hideLoading();
        mView.showFailure();
    }

    @Override
    public void doGetHttpResponse(String url, DocumentDownloader docDownloader, ImageDownloader imgDownloader) {
        mView.showLoading();
        mInteractor.getHttpResponse(url, docDownloader, imgDownloader, this);
    }
}
