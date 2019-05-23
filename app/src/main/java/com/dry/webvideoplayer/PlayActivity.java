package com.dry.webvideoplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {
    private String id;
    private String videoURL;
    private Uri uri;
    private boolean playing = true;

    private VideoView videoView;
    private LinearLayout videoButtonView;
    private PlayActivityTitle playActivityTitle;

    private ImageButton playPauseButton;
    private Handler handler;

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        videoURL = intent.getStringExtra("VIDEO_URL");
        uri = Uri.parse(videoURL);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnTouchListener(mTouchListener);
        playPauseButton.setOnClickListener(mClickListener);
        //        if (this.getResources().getConfiguration().orientation == Configuration
        //        .ORIENTATION_PORTRAIT) {
        //            /* 竖屏 */
        //            playActivityTitle.setVisibility(View.VISIBLE);
        //        } else {
        //            /* 横屏 */
        //            playActivityTitle.setVisibility(View.GONE);
        //        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playActivityTitle = (PlayActivityTitle) findViewById(R.id.play_activity_title);
        videoView = (VideoView) findViewById(R.id.video_view);
        videoButtonView = (LinearLayout) findViewById(R.id.video_button_view);
        playPauseButton = (ImageButton) findViewById(R.id.play_pause_button);
        handler = new Handler();

        videoButtonView.setVisibility(View.GONE);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play_pause_button:
                    if (playing) {
                        playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_play2_48dp));
                        videoView.pause();
                        playing = false;

                    } else {
                        playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause2_48dp));
                        videoView.start();
                        playing = true;
                    }
                    break;
                default:
                    break;

            }
        }
    };

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.video_view:
                    if (videoButtonView.getVisibility() == View.VISIBLE) {
                        videoButtonView.setVisibility(View.GONE);
                    } else {
                        videoButtonView.setVisibility(View.VISIBLE);
                        handler.postDelayed(runnable, 7000);
                    }

                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                videoButtonView.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
