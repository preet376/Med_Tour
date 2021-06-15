package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.destination_detail;
import com.jaypal.navigation_drawer.medicine_center_details;
import com.jaypal.navigation_drawer.model.hospital;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class medicalAdapter extends RecyclerView.Adapter<medicalAdapter.myholder>{
    Context context;
    ArrayList<hospital> hospitals=new ArrayList<>();


    public medicalAdapter(Context context,ArrayList<hospital> hospitals) {
        this.context = context; this.hospitals=hospitals;
    }

    @NonNull
    @Override
    public medicalAdapter.myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.medical_model,parent,false);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull medicalAdapter.myholder holder, final int position) {
        //holder.name_team.setText(list.get(position));
        Picasso.get().load(hospitals.get(position).getLink()).into(holder.imageView_hospital);
        holder.name_hospital.setText(hospitals.get(position).getName());
        holder.addr_hospital.setText(hospitals.get(position).getLocation()+","+hospitals.get(position).getCountry_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, medicine_center_details.class);
               // Bundle bundle=new Bundle();
                //bundle.putInt("int",position);
                //intent.putExtra("bund",bundle);
                intent.putExtra("cname",hospitals.get(position).getCountry_name());
                intent.putExtra("lname",hospitals.get(position).getLocation());
                intent.putExtra("nam",hospitals.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public static class myholder extends RecyclerView.ViewHolder {
        TextView name_hospital;
        ImageView imageView_hospital;
        TextView addr_hospital;
        public myholder(@NonNull View view) {

            super(view);
            imageView_hospital = view.findViewById(R.id.image_hospital);
             addr_hospital=view.findViewById(R.id.te2);
            //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            name_hospital = view.findViewById(R.id.te1);

        }
    }
}
