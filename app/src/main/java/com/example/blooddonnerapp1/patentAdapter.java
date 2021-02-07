package com.example.blooddonnerapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class patentAdapter extends FirebaseRecyclerAdapter<Model, patentAdapter.mypatentholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public patentAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull mypatentholder holder, int position, @NonNull Model model) {
holder.name.setText(model.getNamevalue());
        holder.address.setText(model.getAddressvalue());
        holder.phone.setText(model.getPhonevalue());
        holder.bloodgroup.setText(model.getBloodgropvalue());
    }

    @NonNull
    @Override
    public mypatentholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patent_layout, parent, false);
        return new mypatentholder(view);

    }

    public class mypatentholder extends RecyclerView.ViewHolder {
        TextView name, phone, address, bloodgroup;

        public mypatentholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
            bloodgroup = itemView.findViewById(R.id.bloodgroup);

        }
    }
}
