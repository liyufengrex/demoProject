//package com.rex.myapplication.ui;
//
//import android.graphics.drawable.Drawable;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.load.resource.bitmap.CircleCrop;
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;
//import com.rex.myapplication.R;
//
//public class Main3Activity extends AppCompatActivity {
//
//    private ImageView img;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
//
//        initView();
//        loadData();
//    }
//
//
//    private void initView() {
//        img = findViewById(R.id.img);
//    }
//
//    private void loadData() {
//        Glide.with(this)
//                .load("https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00202-3179.jpg")
//                .transition(DrawableTransitionOptions.withCrossFade(500)) // 渐变
//                .transform(new CircleCrop()) // 圆形图片
//                .transform(new RoundedCorners(20)) // 圆角图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.i("rex", "fail");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        Log.i("rex", "ready");
//                        return false;
//                    }
//                })
//                .into(img);
//    }
//
//}
