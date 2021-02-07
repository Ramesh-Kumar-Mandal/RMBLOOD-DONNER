package com.example.blooddonnerapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class DetialFragmet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
List<dataholder>list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
String name,email,address,phone,image,bloodgroup;
    public DetialFragmet() {
        // Required empty public constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public DetialFragmet(String name, String phone, String address, String email, String image, String bloodgroup) {

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.image = image;
        this.bloodgroup = bloodgroup;
    }


    public static DetialFragmet newInstance(String param1, String param2) {
        DetialFragmet fragment = new DetialFragmet();
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
        View view = inflater.inflate(R.layout.fragment_detial_fragmet, container, false);
        ImageView image1 = view.findViewById(R.id.iamge1);
        TextView username = view.findViewById(R.id.username);
        TextView email1 = view.findViewById(R.id.email);
        TextView phoneno = view.findViewById(R.id.phoneno);
        TextView address1 = view.findViewById(R.id.address);
        TextView bloodgroup1 = view.findViewById(R.id.bloodgroup1);

        username.setText(name);
        email1.setText(email);
        phoneno.setText(phone);
        address1.setText(address);
        bloodgroup1.setText(bloodgroup);
        Glide.with(getContext()).load(image).into(image1);

        return  view;



    }
}