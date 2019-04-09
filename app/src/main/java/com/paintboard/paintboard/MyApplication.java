package com.paintboard.paintboard;

import android.app.Application;

import com.vangogh.downloader.DocumentDownloader;
import com.vangogh.downloader.ImageDownloader;

public class MyApplication extends Application {
    private ImageDownloader imageDownloader;
    private DocumentDownloader documentDownloader;

    @Override
    public void onCreate() {
        super.onCreate();

        imageDownloader = new ImageDownloader();
        documentDownloader = new DocumentDownloader();
    }

    public ImageDownloader getImageDownloader() {
        return imageDownloader;
    }

    public void setImageDownloader(ImageDownloader imageDownloader) {
        this.imageDownloader = imageDownloader;
    }

    public DocumentDownloader getDocumentDownloader() {
        return documentDownloader;
    }

    public void setDocumentDownloader(DocumentDownloader documentDownloader) {
        this.documentDownloader = documentDownloader;
    }
}
