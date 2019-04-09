package com.paintboard.paintboard;

public class MainPresenterImpl implements MainPresenter, MainInteractor.MainInteractorListener {
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenterImpl(MainView mView) {
        this.mView = mView;
        mInteractor = new MainInteractorImpl();
    }

    @Override
    public void onSuccess() {
        mView.hideLoading();
        mView.showContent();
    }

    @Override
    public void onFailed() {
        mView.hideLoading();
        mView.showFailure();
    }

    @Override
    public void doGetHttpResponse(String url) {
        mView.showLoading();
        mInteractor.getHttpResponse(url, this);
    }
}
