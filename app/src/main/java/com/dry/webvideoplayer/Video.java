package com.dry.webvideoplayer;

import android.util.Log;

public class Video {
    private int id = -1;
    private String name = "";
    private String videoURL;
    private String imgURL;
    private String type = "MP4 video";
    private String size = "28.2 MB";
    private String duration = "2 minutes 53 seconds";
    private String dimensions = "1920 x 1080";

    public Video(int id, String name, String videoURL, String imgURL, String type, String size, String duration,
                 String dimensions) {
        this.id = id;
        this.name = name;
        this.videoURL = videoURL;
        this.imgURL = imgURL;
        this.type = type;
        this.size = size;
        this.duration = duration;
        this.dimensions = dimensions;
        Log.v("110", "New an instance of Video");
    }

    public Video(int id, String name, String videoURL, String imgURL) {
        this.id = id;
        this.name = name;
        this.videoURL = videoURL;
        this.imgURL = imgURL;
        Log.v("110", "New an instance of Video");
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getVideoURL() {
        return this.videoURL;
    }

    public String getImgURL() {
        return this.imgURL;
    }

    public String getType() {
        return this.type;
    }

    public String getSize() {
        return this.size;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getDimensions() {
        return this.dimensions;
    }

    public String getInfo() {
        return this.type + "\t" + this.size + "\t" + this.duration + "\t" + this.dimensions;
    }

}
