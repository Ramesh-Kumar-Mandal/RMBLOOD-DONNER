package com.example.blooddonnerapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class patentfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    patentAdapter adapater;
RecyclerView recycleview1;
    public patentfragment() {
        // Required empty public constructor
    }

    public static patentfragment newInstance(String param1, String param2) {
        patentfragment fragment = new patentfragment();
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
      View view= inflater.inflate(R.layout.fragment_patentfragment, container, false);
      recycleview1=(RecyclerView)view.findViewById(R.id.recycleview1);
        recycleview1.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Patent"), Model.class)
                        .build();
        adapater=new patentAdapter(options);
        recycleview1.setAdapter(adapater);
        return view;

    }
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