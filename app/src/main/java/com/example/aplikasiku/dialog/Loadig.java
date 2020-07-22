package com.example.aplikasiku.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aplikasiku.R;
import com.example.aplikasiku.index.ContentBook;

public class Loadig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imLoading = (ImageView) findViewById(R.id.loadingView);
        imLoading.setBackgroundResource(R.drawable.loading);
        AnimationDrawable frameAnimation = (AnimationDrawable) imLoading
                .getBackground();
        //Menjalankan File Animasi
        frameAnimation.start();
    }

}