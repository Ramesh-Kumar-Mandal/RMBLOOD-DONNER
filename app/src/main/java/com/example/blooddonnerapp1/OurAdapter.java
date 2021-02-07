package com.example.blooddonnerapp1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class OurAdapter extends FirebaseRecyclerAdapter<dataholder, OurAdapter.myviewer> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OurAdapter(@NonNull FirebaseRecyclerOptions<dataholder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewer holder, int position, @NonNull dataholder model) {
        holder.username.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.phoneno.setText(model.getPhone());
        holder.bloodgroup.setText(model.getBloodgroup());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Activity, new DetialFragmet(model.getName(), model.getAddress(), model.getPhone(), model.getDob(), model.getImage(), model.getBloodgroup())).addToBackStack(null).commit();
            }
        });
        //holder.edit.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   AlertDialog.Builder builder = new AlertDialog.Builder(holder.image.getContext());
              //  builder.setTitle("Delete Panel");
               // builder.setMessage("Are you sure");
                //
                //ilder.setNegativeButton("yes",n)
                  //
                  //      .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    ////        @Override
                      //      public void onClick(DialogInterface dialog, int which) {

                    //        }
                      //  });
               // builder.show();
        //    }
      //  });

    }

    @NonNull
    @Override
    public myviewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout, parent, false);
        return new myviewer(view);
    }

    public class myviewer extends RecyclerView.ViewHolder {
        ImageView image, edit;
        TextView username, email, phoneno, bloodgroup;

        public myviewer(@NonNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.image);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phoneno = itemView.findViewById(R.id.phoneno);
            bloodgroup = itemView.findViewById(R.id.bloodgroup);
        }
    }
}
