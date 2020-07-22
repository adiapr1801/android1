package com.example.aplikasiku.index;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasiku.R;
import com.example.aplikasiku.login.LoginActivity;

public class Splashku extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashku);
        
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(Splashku.this, LoginActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}