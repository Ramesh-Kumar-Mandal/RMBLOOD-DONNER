package com.example.blooddonnerapp1.USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blooddonnerapp1.HomeActivity;
import com.example.blooddonnerapp1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdduserActivity extends AppCompatActivity {
    EditText uname, uphone, ubloodgroup, uemail;
    Button submmit, cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        uname = findViewById(R.id.uname);
        uphone = findViewById(R.id.uphone);
        ubloodgroup = findViewById(R.id.ublood);
        uemail = findViewById(R.id.uemail);
        submmit = findViewById(R.id.submmit);
        cancle = findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdduserActivity.this, HomeActivity.class));
            }
        });
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInsert();
            }
        });


    }

    private void processInsert() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", uname.getText().toString());
        map.put("phone", uphone.getText().toString());
        map.put("email", uemail.getText().toString());
        map.put("bloodgroup", ubloodgroup.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("Student").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        uname.setText("");
                        uemail.setText("");
                        uphone.setText("");
                        ubloodgroup.setText("");
                        Toast.makeText(AdduserActivity.this, "Insert Sucess", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdduserActivity.this, "Insert fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}