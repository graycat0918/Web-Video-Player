package com.dry.webvideoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private List<Video> videoList;
    private Context context;
    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View videoView;
        TextView videoName;
        ImageView videoImg;
        ProgressBar progressBar;

        private ViewHolder(View view) {
            super(view);
            videoView = view;
            videoName = (TextView) view.findViewById(R.id.video_name);
            videoImg = (ImageView) view.findViewById(R.id.video_img);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }

    public VideoAdapter(Context context, List<Video> videoList) {
        this.videoList = videoList;
        this.context = context;
        this.activity = ActivityController.getActivity(this.context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.videoView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, PlayActivity.class);
                Video video = videoList.get(position);
                String toastText = video.getName() + " " + video.getVideoURL();
                Toast.makeText(v.getContext(), toastText, Toast.LENGTH_SHORT).show();

                intent.putExtra("ID", video.getID());
                intent.putExtra("VIDEO_URL", video.getVideoURL());
                activity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.videoName.setText(video.getName());
        ImgLoadAsyncTask task = new ImgLoadAsyncTask(holder.progressBar, holder.videoImg);
        task.execute(video.getImgURL());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }


}
