package com.example.blooddonnerapp1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class RecFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
RecyclerView recyclerView;
OurAdapter adapater;
    public RecFragment() {
    }

    public static RecFragment newInstance(String param1, String param2) {
        RecFragment fragment = new RecFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rec, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<dataholder> options =
                new FirebaseRecyclerOptions.Builder<dataholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), dataholder.class)
                        .build();
        adapater=new OurAdapter(options);
        recyclerView.setAdapter(adapater);
        return view;
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

}