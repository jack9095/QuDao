package com.kuanquan.qudao.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * @author Rayhahah
 * @blog http://rayhahah.com
 * @time 2017/10/18
 * @tips 这个类是Object的子类
 * @fuction
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //保存在 SDCard/Android/data/com.rayhahah.easysports/cache/glide目录下
        //缓存大小500M（超过就会按照LruCache算法清除）
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "glide", 500 * 1024 * 1024));
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new ProgressInterceptor())
//                .build();
//        glide.register(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(okHttpClient));
//        String url = "";
//        ImageView view = new ImageView(context);
//        ProgressInterceptor.addListener(url, new ProgressListener() {
//            @Override
//            public void onProgress(int progress) {
//
//            }
//        });
//        Glide.with(context)
//                .load(url)
//                .into(new GlideDrawableImageViewTarget(view) {
//                    @Override
//                    public void onLoadStarted(Drawable placeholder) {
//                        super.onLoadStarted(placeholder);
//                    }
//
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
//                        super.onResourceReady(resource, animation);
//                    }
//                });


    }
}
