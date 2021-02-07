package com.example.blooddonnerapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ContectUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_u_s);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Contect Us");

    }
}