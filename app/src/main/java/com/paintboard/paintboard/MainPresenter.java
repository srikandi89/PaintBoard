package com.paintboard.paintboard;

import com.vangogh.downloader.DocumentDownloader;
import com.vangogh.downloader.ImageDownloader;

public interface MainPresenter {
    void doGetHttpResponse(String url, DocumentDownloader docDownloader, ImageDownloader imgDownloader);
}
