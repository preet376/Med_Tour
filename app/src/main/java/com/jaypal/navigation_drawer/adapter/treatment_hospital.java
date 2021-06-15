package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.medicine_center_details;
import com.jaypal.navigation_drawer.tratement_details;

import java.util.ArrayList;

public class treatment_hospital extends RecyclerView.Adapter<treatment_hospital.mholder> {
    String[]names={"Chronic back pain","Thigh Lift","Pelvic Florr Disorders Treatment","Foot & Ankle Deformity","Tendinosis","Bursitis","Capsulitis","Subluxations","Tendon Dysfunctions",
    "Subluxations","Flat Feet"};
    Context context;
    ArrayList<String>treat=new ArrayList<>();
    public treatment_hospital(Context co, ArrayList<String>treat) {
        this.context=co;this.treat=treat;
    }

    @NonNull
    @Override
    public mholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_treatment_hospital,parent,false);
        return new mholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mholder holder, final int position) {

        holder.textView.setText(treat.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, tratement_details.class);
                intent.putExtra("tname",treat.get(position));
                context.startActivity(intent);
            }
        });

    }




    @Override
    public int getItemCount() {
       return treat.size();

    }

    public static class mholder extends RecyclerView.ViewHolder {
        TextView textView;
        public mholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.treatment_name);
        }
    }
}
