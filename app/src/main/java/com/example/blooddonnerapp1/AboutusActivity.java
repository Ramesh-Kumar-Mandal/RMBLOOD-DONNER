package com.example.blooddonnerapp1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AboutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutusfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("About Us");

    }
}