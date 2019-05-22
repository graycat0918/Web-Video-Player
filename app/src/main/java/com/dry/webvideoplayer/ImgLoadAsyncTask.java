package com.dry.webvideoplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImgLoadAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private ProgressBar progressBar;
    private ImageView imageView;

    public ImgLoadAsyncTask(ProgressBar progressBar, ImageView imageView) {
        this.progressBar = progressBar;
        this.imageView = imageView;

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null; // 待返回的结果
        String url = params[0]; // 获取URL
        URLConnection connection; // 网络连接对象
        InputStream inStream; // 数据输入流
        try {
            connection = new URL(url).openConnection();
            inStream = connection.getInputStream(); //获取输入流
            BufferedInputStream buf = new BufferedInputStream(inStream);
            // 解析输入流
            bitmap = BitmapFactory.decodeStream(buf);
            inStream.close();
            buf.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回给后面调用的方法
        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        //显示等待圆环
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        //下载完毕,隐藏等待圆环
        this.progressBar.setVisibility(View.GONE);
        this.imageView.setImageBitmap(result);
    }
}

