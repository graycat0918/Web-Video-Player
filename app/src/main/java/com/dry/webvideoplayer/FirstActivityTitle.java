package com.dry.webvideoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivityTitle extends LinearLayout {
    private ImageButton leftButton;
    private ImageButton rightButton;
    private TextView centerTitle;

    public FirstActivityTitle(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_template, this);

        leftButton = (ImageButton) findViewById(R.id.left_button);
        rightButton = (ImageButton) findViewById(R.id.right_button);
        centerTitle = (TextView) findViewById(R.id.center_title);

        centerTitle.setText(R.string.app_name);
        leftButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_24dp));
        rightButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_list_24dp));

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Back", Toast.LENGTH_SHORT).show();

                ((Activity) getContext()).finish();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context, ListActivity.class);
                ActivityController.getActivity(context).startActivity(mIntent);
            }
        });

    }
}
