package com.example.blooddonnerapp1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class pplashACtivity extends AppCompatActivity {
Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pplash_a_ctivity);


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(pplashACtivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);


    }
}