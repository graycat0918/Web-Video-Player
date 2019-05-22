package com.dry.webvideoplayer;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class VideoDBHelper extends SQLiteOpenHelper {

    private static final String CREATE_VIDEO_TABLE = "create table video ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "video_url text, "
            + "img_url text, "
            + "type text,"
            + "size text,"
            + "duration text,"
            + "dimensions text)";

    private Context context;

    public VideoDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VIDEO_TABLE);
        Toast.makeText(this.context, "Create video table", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists video");
        onCreate(db);
    }
}
