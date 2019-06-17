package com.example.connectfour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.os.Handler;





public class splash extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        int TIME_LENGTH = 3000;
        new Handler().postDelayed(new Runnable(){
        @Override
        public void run(){
        Intent homeIntent= new Intent(splash.this, MainActivity.class);
        startActivity(homeIntent);
        finish();}
        }, TIME_LENGTH);
    }}