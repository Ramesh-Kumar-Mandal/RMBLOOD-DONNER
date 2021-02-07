package com.example.blooddonnerapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class MyAdapater extends FirebaseRecyclerAdapter<ModelClass,MyAdapater.myviewholder> {


    public MyAdapater(@NonNull FirebaseRecyclerOptions<ModelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ModelClass model) {
      holder.username.setText(model.getUsernamevalue());
        holder.email.setText(model.getEmailvalue());
        holder.phoneno.setText(model.getPhonenovalue());
        holder.bloodgroup.setText(model.getBloodgroupvalue());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Activity, new RecFragment()).addToBackStack(null).commit();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout,parent,false);
     return new myviewholder(view);
    }


    public  class myviewholder extends RecyclerView.ViewHolder{
        ImageView image;
TextView username,email,phoneno,bloodgroup;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

image=itemView.findViewById(R.id.image);
            username=itemView.findViewById(R.id.username);
            email=itemView.findViewById(R.id.email);
            phoneno=itemView.findViewById(R.id.phoneno);
            bloodgroup=itemView.findViewById(R.id.bloodgroup);
        }
    }
}
