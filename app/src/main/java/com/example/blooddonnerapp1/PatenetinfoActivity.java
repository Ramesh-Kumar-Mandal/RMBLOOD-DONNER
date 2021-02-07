package com.example.blooddonnerapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class PatenetinfoActivity extends AppCompatActivity {
    patentAdapter adapater;
    RecyclerView recycleview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patenetinfo);
        getSupportFragmentManager().beginTransaction().replace(R.id.remove,new patentfragment()).commit();
    }
}