package com.dry.webvideoplayer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {
    private String id;
    private String videoURL;
    private VideoView videoView;
    private Uri uri;

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        videoURL = intent.getStringExtra("VIDEO_URL");
        uri = Uri.parse(videoURL);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        videoView = (VideoView) findViewById(R.id.video_view);
    }
}
