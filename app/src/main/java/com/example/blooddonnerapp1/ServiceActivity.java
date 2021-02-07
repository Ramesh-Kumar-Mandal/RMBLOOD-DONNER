package com.example.blooddonnerapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity  implements View.OnClickListener{
Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==start){
            startService(new Intent(this,ServiceClass.class));
        }else if (v==stop){
            stopService(new Intent(this,ServiceClass.class));

        }
    }
}