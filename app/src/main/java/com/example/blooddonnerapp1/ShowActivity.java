package com.example.blooddonnerapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowActivity extends AppCompatActivity {
Button loginhere,registerhere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        loginhere=findViewById(R.id.loginhere);
        registerhere=findViewById(R.id.registerhere);
        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,LoginActivity.class));
            }
        });
        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowActivity.this,RegisterShotActivity.class));
            }
        });
    }
}