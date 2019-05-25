package com.dry.webvideoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class PlayFragment extends Fragment {
    private View view;
    private int id;
    private String videoURL;
    private Uri uri;
    private boolean playing = true;

    private VideoView videoView;
    private LinearLayout videoButtonView;
//    private PlayActivityTitle playActivityTitle;

    private ImageButton playPauseButton;
    private Handler handler;

    @Override
    public void onResume() {
        super.onResume();
//        uri = Uri.parse(videoURL);
//        videoView.setVideoURI(uri);
//        videoView.start();
        videoView.setOnTouchListener(mTouchListener);
        playPauseButton.setOnClickListener(mClickListener);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play, container, false);
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);

//        playActivityTitle = (PlayActivityTitle) view.findViewById(R.id.play_activity_title);
        videoView = (VideoView) view.findViewById(R.id.video_view);
        videoButtonView = (LinearLayout) view.findViewById(R.id.video_button_view);
        playPauseButton = (ImageButton) view.findViewById(R.id.play_pause_button);
        handler = new Handler();

        videoButtonView.setVisibility(View.GONE);
        return view;
    }

    public void setData(int id, String videoURL) {
        this.id = id;
        this.videoURL = videoURL;
        uri = Uri.parse(videoURL);
        videoView.setVideoURI(uri);
        videoView.start();
        //        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        //        visibilityLayout.setVisibility(View.VISIBLE);
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
