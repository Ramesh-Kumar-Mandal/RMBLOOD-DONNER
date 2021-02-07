package com.example.blooddonnerapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class HOMEMAINActivity extends AppCompatActivity {
    ImageView image;
    CardView finddonner, bloodbank, maap, donerlist, aboutus, logout, contectus;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        this.setTitle("Home");

        finddonner = findViewById(R.id.finddonner);

        bloodbank = findViewById(R.id.bloodbank);
        maap = findViewById(R.id.map);
        donerlist = findViewById(R.id.donerlist);

        logout = findViewById(R.id.logout);
        contectus = findViewById(R.id.contectus);
        mAuth = FirebaseAuth.getInstance();
        finddonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOMEMAINActivity.this, USerlistActivity.class));
            }
        });
        maap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOMEMAINActivity.this, MAPONActivity.class));
            }
        });
        bloodbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOMEMAINActivity.this, BloodBankWebview.class));
            }
        });
        donerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HOMEMAINActivity.this, HomeActivity.class));
            }
        });


        contectus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HOMEMAINActivity.this, ContectUsActivity.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(HOMEMAINActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = 0;
        switch (id) {
            case R.id.menu1:
                startActivity(new Intent(HOMEMAINActivity.this, HomeActivity.class));
                break;
            case R.id.logout:
                mAuth.signOut();
                startActivity(new Intent(HOMEMAINActivity.this, USerlistActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}