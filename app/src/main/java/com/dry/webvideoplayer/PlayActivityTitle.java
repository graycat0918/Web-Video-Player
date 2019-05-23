package com.dry.webvideoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivityTitle extends LinearLayout {
    private ImageButton leftButton;
    private ImageButton rightButton;
    private TextView centerTitle;
    private Activity activity;

    public PlayActivityTitle(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_template, this);

        leftButton = (ImageButton) findViewById(R.id.left_button);
        rightButton = (ImageButton) findViewById(R.id.right_button);
        centerTitle = (TextView) findViewById(R.id.center_title);
        activity = ActivityController.getActivity(context);

        centerTitle.setText(R.string.video);
        leftButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_24dp));
        rightButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_refresh_24dp));

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Back", Toast.LENGTH_SHORT).show();
                ((Activity) getContext()).finish();
            }
        });

//        rightButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Refresh video list", Toast
//                        .LENGTH_SHORT).show();
//                //                Intent intent = new Intent(context, WritePoemActivity.class);
//                //                ActivityController.getActivity(context).startActivityForResult
//                //                (intent,WRITE_POEM_ACTIVITY);
//                ActivityController.getActivity(context).finish();
//                Intent intent = new Intent(context, ListActivity.class);
//                activity.startActivity(intent);
//            }
//        });
    }
}
