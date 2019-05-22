package com.dry.webvideoplayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ScrollView;

public class VideoDBManager {
    private Context context;
    private SQLiteDatabase db;
    private ContentValues values;

    public VideoDBManager(Context context) {
        this.context = context;
        VideoDBHelper dbHelper = new VideoDBHelper(context, "VideoStore.db", null, 1);
        this.db = dbHelper.getWritableDatabase();
        this.values = new ContentValues();
    }

    public void init(String table) {
        if (!query(table, null, null, null, null, null, null).moveToFirst()) {
            for (String[] item : ResourceHelper.get2DResArray(this.context, table + "_array")) {
                //                item[3] = new RegexManager(item[3], " ").replaceAll("");
                insert(table, item[0], item[1], item[2], null, null, null, null);
            }
        }
    }

    public void insert(String table, String name, String videoURL, String imgURL, String type,
                       String size, String duration, String dimensions) {
        values.put("name", name);
        values.put("video_url", videoURL);
        values.put("img_url", imgURL);
        values.put("type", type);
        values.put("size", size);
        values.put("duration", duration);
        values.put("dimensions", dimensions);
        db.insert(table, null, values);
        values.clear();
    }

    public void update(String table, String key, String newValue, String whereClause,
                       String[] whereArgs) {
        values.put(key, newValue);
        db.update(table, values, whereClause, whereArgs);
        values.clear();
    }

    public void update(String table, String key, float newValue, String whereClause,
                       String[] whereArgs) {
        values.put(key, newValue);
        db.update(table, values, whereClause, whereArgs);
        values.clear();
    }

    public void delete(String table, String whereClause, String[] whereArgs) {
        db.delete(table, whereClause, whereArgs);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs,
                        String groupBy, String having, String orderBy) {
        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having,
                orderBy);
        return cursor;
    }
}
