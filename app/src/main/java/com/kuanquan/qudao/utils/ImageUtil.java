package com.kuanquan.qudao.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kuanquan.qudao.R;

/**
 * Created by able on 2018/4/17.
 */

public class ImageUtil {
    public static void loadPicture(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url) && url.contains(".gif")) {
            Glide.with(context.getApplicationContext()).load(url)
                    .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).centerCrop().into(imageView);
        } else {
            Glide.with(context.getApplicationContext()).load(url).asBitmap()
                    .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).centerCrop().into(imageView);
        }
    }
}
