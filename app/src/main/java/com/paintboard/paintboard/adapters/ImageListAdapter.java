package com.paintboard.paintboard.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.paintboard.paintboard.R;
import com.paintboard.paintboard.models.ImageContent;
import com.vangogh.downloader.ImageDownloader;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.MyViewHolder> {
    private List<ImageContent> contents;
    private Context context;
    private ImageDownloader imageDownloader;
    private ImageDownloader.OnDownloadListener downloadListener;

    public ImageListAdapter(Context context, List<ImageContent> contents, ImageDownloader imageDownloader) {
        this.context = context;
        this.contents = contents;
        this.imageDownloader = imageDownloader;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.content_list_item, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        final ImageContent content = contents.get(i);
        Log.d(ImageListAdapter.class.getSimpleName(), "IMAGE #"+i);

        holder.progressBar.setVisibility(View.VISIBLE);
        holder.imageView.setVisibility(View.GONE);

        downloadListener = new ImageDownloader.OnDownloadListener() {
            @Override
            public void onDownloadFinished() {
                holder.progressBar.setVisibility(View.GONE);
                holder.imageView.setVisibility(View.VISIBLE);
                holder.cancelBtn.setText("Re-download");
            }

            @Override
            public void onDownloadStarted() {
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.imageView.setVisibility(View.GONE);
                holder.cancelBtn.setText("Cancel");
            }

            @Override
            public void onDownloadStopped() {
                holder.cancelBtn.setText("Retry");
                holder.imageView.setVisibility(View.VISIBLE);
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onDownloadFailed() {
                holder.cancelBtn.setText("Retry");
                holder.imageView.setVisibility(View.VISIBLE);
                holder.progressBar.setVisibility(View.GONE);
            }
        };

        imageDownloader.toImageView(content.getUrl(), holder.imageView, downloadListener);

        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cancelBtn.getText().toString().toLowerCase().equals("cancel")) {
                    Log.d(ImageListAdapter.class.getSimpleName(), "Download Image from "+content.getUrl()+" about to stop");

                    imageDownloader.cancel(content.getUrl());

                    holder.cancelBtn.setText("Restart");
                }
                else if (holder.cancelBtn.getText().toString().toLowerCase().equals("restart")) {
                    Log.d(ImageListAdapter.class.getSimpleName(), "Download image from "+content.getUrl()+" about to started");
                    imageDownloader.toImageView(content.getUrl(), holder.imageView, downloadListener);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return contents != null ? contents.size() : 0;
    }

    /**
     * Use static to keep each of items will displayed consistently
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public Button cancelBtn;
        public ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            cancelBtn = itemView.findViewById(R.id.btn_cancel);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }
}
