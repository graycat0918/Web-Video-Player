package com.dry.webvideoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayActivity extends AppCompatActivity {
    private PlayFragment playFragment;
    private int id;
    private String videoURL;

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        videoURL = intent.getStringExtra("VIDEO_URL");
        playFragment.setData(id, videoURL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playFragment = (PlayFragment) getSupportFragmentManager().findFragmentById(R.id.play_fragment_layout);
    }
}
