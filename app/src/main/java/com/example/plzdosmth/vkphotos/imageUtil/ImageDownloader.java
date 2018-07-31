package com.example.plzdosmth.vkphotos.imageUtil;

import android.widget.ImageView;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ImageDownloader {

    public static ImageDownloader imageDownloader;
    private ThreadPoolExecutor   threadPoolExecutor;


    private ImageDownloader() {
        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new BlockingLifoQueue<Runnable>());
    }

    public static ImageDownloader getInstance(){

        if(imageDownloader ==null){
            imageDownloader =new ImageDownloader();
        }
        return imageDownloader;
    }

    public void download(ImageView imageView, String url) {
        threadPoolExecutor.execute(new DownloadManager(imageView, url));
    }

}
