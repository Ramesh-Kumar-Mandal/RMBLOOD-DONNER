package com.example.blooddonnerapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.blooddonnerapp1.USER.AdduserActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OurAdapter adapater;
    FloatingActionButton adduser;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recycleview);
        adduser = findViewById(R.id.adduser);
        progress = findViewById(R.id.progress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Donner List ");
        progress.setVisibility(View.VISIBLE);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AdduserActivity.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<dataholder> options =
                new FirebaseRecyclerOptions.Builder<dataholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), dataholder.class)
                        .build();
        adapater = new OurAdapter(options);
        recyclerView.setAdapter(adapater);
        progress.setVisibility(View.GONE);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapater.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapater.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        MenuItem item = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void processSearch(String query) {
        FirebaseRecyclerOptions<dataholder> options =
                new FirebaseRecyclerOptions.Builder<dataholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Student").orderByChild("bloodgroup").startAt(query).endAt(query + "\uff88"), dataholder.class)
                        .build();
        adapater = new OurAdapter(options);
        adapater.startListening();
        recyclerView.setAdapter(adapater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = 0;
        switch (id) {
            case R.id.HOMEH:
                startActivity(new Intent(HomeActivity.this, HOMEMAINActivity.class));
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
