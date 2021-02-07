package com.example.blooddonnerapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.myviewholder> implements Filterable {
    Context context;
    List<dataholder>list;
    List<dataholder>listfill;
    dataholder model;

     public  NewAdapter(Context context,List<dataholder>list){
         this.context =context;
         this.list=list;
         listfill=new ArrayList<>(list);
     }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_layout, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
       holder.username.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
        holder.phoneno.setText(list.get(position).getPhone());
        holder.bloodgroup.setText(list.get(position).getBloodgroup());
        Glide.with(holder.image.getContext()).load(list.get(position).getImage()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Activity, new DetialFragmet(list.get(position).getName(),list.get(position).getAddress(),list.get(position).getPhone(),list.get(position).getDob(),list.get(position).getImage(),list.get(position).getBloodgroup())).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return  filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
         String searchText=constraint.toString().toLowerCase();
         List<dataholder>templist=new ArrayList<>();
         if (searchText.isEmpty()){
             templist.addAll(listfill);
         }else{
             for (dataholder item:listfill ){
                 if (item.getBloodgroup().toLowerCase().contains(searchText)){
                     templist.add(item);
                 }
             }
         }
      FilterResults filterResults=new FilterResults();
         filterResults.values=templist;
         return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
list.clear();
list.addAll((Collection<? extends dataholder>) results.values);
notifyDataSetChanged();
        }
    };

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView username, email, phoneno, bloodgroup;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phoneno = itemView.findViewById(R.id.phoneno);
            bloodgroup = itemView.findViewById(R.id.bloodgroup);

        }
    }
}
