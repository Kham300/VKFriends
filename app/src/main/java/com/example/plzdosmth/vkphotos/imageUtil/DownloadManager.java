package com.example.plzdosmth.vkphotos.imageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DownloadManager extends Thread {
    private static final String TAG = "DOWNLOADMANAGER";

    private ImageView imageView;
    private String url;
    private Handler handler;
    private File file;
    private Context context;
    private Bitmap bitmap;


    DownloadManager(ImageView imageView, String url) {
        handler = new Handler();
        this.imageView = imageView;
        this.url = url;
        context = imageView.getContext();
        file = new File(context.getCacheDir(), createFileName(url));
    }

    DownloadManager(String url){
        this.url=url;
    }



    @Override
    public void run() {
        super.run();
        if (file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            Log.i(TAG, "getting image from cache " + file.getName());
            setImage();
        } else {
            bitmap = getBitmapFromURL(url);
            setImage();
            if (bitmap != null) {
                savePicture(bitmap);
            }
        }
    }

    private void setImage() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                    imageView.setImageBitmap(bitmap);
            }
        });
    }


    private void savePicture(Bitmap bitmap){
        OutputStream out;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);
            out.flush();
            out.close();

            Log.i(TAG, "file saved in folder " + context.getCacheDir() + " name " + file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //to download an image
    private static Bitmap getBitmapFromURL(String s) {
        try {
            byte[] bitmapBytes = getUrlBytes(s);
            return BitmapFactory.decodeByteArray(bitmapBytes,0, bitmapBytes.length);
        } catch (IOException ioe){
            Log.e(TAG,"Error downloading image");
        }
        return null;
    }

    public static byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String createFileName(String url){
        String result = url.substring(6, url.length() - 11);
        result = result.replace("/", "");
        result = result.replace(".", "");
        return result;
    }

}
