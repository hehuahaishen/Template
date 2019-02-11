package com.example.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.common.R;

import static android.widget.ImageView.ScaleType.FIT_CENTER;

/**
 * 图片加载框架
 */
public class ImageUtils {

    public static void loadImg(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading)
                .error(R.drawable.faill)
                .centerCrop();

        Glide.with(context).load(url).apply(options).into(imageView);
    }


    public static void loadImgRect(Context context, String url, ImageView imageView) {
        imageView.setScaleType(FIT_CENTER);
        // imageView.setScaleType(FIT_XY);
        // imageView.setScaleType(CENTER_INSIDE);
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL)            //缓存所有版本的图像
                .placeholder(R.drawable.loading)
                .error(R.drawable.faill);

        Glide.with(context).load(url).apply(options).into(imageView);
    }

}
