package com.dry.webvideoplayer;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private List<Video> videoList = new ArrayList<>();
    private VideoDBManager dbManager;
    private Cursor cursor;

    private int id;
    private String name;
    private String videoURL;
    private String imgURL;
    private String type;
    private String size;
    private String duration;
    private String dimensions;

    private int lastPosition = 0;
    private int lastOffset = 0;

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.video_recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        VideoAdapter adapter = new VideoAdapter(this, videoList);
        recyclerView.setAdapter(adapter);

        // Go back last position of RecyclerView.
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View topView = layoutManager.getChildAt(0);
                // Get position when topView != null
                if (topView != null) {
                    lastOffset = topView.getTop();
                    lastPosition = layoutManager.getPosition(topView);
                    Log.v("110", "last off set: " + lastOffset);
                    Log.v("110", "last position: " + lastPosition);
                }
            }
        });

        try {
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(lastPosition,
                    lastOffset);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbManager = new VideoDBManager(this);
        cursor = dbManager.query("video", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"));
                name = cursor.getString(cursor.getColumnIndex("name"));
                videoURL = cursor.getString(cursor.getColumnIndex("video_url"));
                imgURL = cursor.getString(cursor.getColumnIndex("img_url"));
                type = cursor.getString(cursor.getColumnIndex("type"));
                size = cursor.getString(cursor.getColumnIndex("size"));
                duration = cursor.getString(cursor.getColumnIndex("duration"));
                dimensions = cursor.getString(cursor.getColumnIndex("dimensions"));

                videoList.add(new Video(id, name, videoURL, imgURL));
                Log.v("110", id + " " + name + " " + videoURL + " " + imgURL);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
