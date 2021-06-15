package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jaypal.navigation_drawer.MainActivity;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.medicine_center_details;
import com.jaypal.navigation_drawer.model.hospital;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.content.ContentValues.TAG;

public class medicalAdapter_2 extends RecyclerView.Adapter<medicalAdapter_2.myh> implements Filterable {
       List<hospital>filtered;
       List<hospital>hos;
    Context context;
    public medicalAdapter_2(Context context, ArrayList<hospital> hospitals) {
        this.context = context;
        this.filtered=hospitals;
        this.hos=hospitals;

    }

    @NonNull
    @Override
    public myh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.medical_model_2,parent,false);
        return new myh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull medicalAdapter_2.myh holder, final int position) {

            {
                Picasso.get().load(filtered.get(position).getLink()).into(holder.imageView_hospital);
            }
            holder.name_hospital.setText(filtered.get(position).getName());
            holder.addr_hospital.setText(filtered.get(position).getLocation()+","+filtered.get(position).getCountry_name());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, medicine_center_details.class);
                    intent.putExtra("cname",filtered.get(position).getCountry_name());
                    intent.putExtra("lname",filtered.get(position).getLocation());
                    intent.putExtra("nam",filtered.get(position).getName());
                   context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();
                if (charString.isEmpty())
                {
                    filtered=hos;
                }
                else {
                    List<hospital>d=new ArrayList<>();
                    for (hospital a :hos)
                    {
                        if (a.getName().toLowerCase().trim().contains(charString.toLowerCase().trim()))
                        {
                            d.add(a);
                        }
                    }
                    filtered=d;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                 filtered=(ArrayList<hospital>)filterResults.values;
                 notifyDataSetChanged();
            }
        };
    }

    public static class myh extends RecyclerView.ViewHolder {
        TextView name_hospital;
        ImageView imageView_hospital;
        TextView addr_hospital;
        ShimmerFrameLayout sh;
        public myh(@NonNull View view) {

            super(view);

            imageView_hospital = view.findViewById(R.id.image_hospital4);
            addr_hospital=view.findViewById(R.id.te24);
           name_hospital = view.findViewById(R.id.te14);

        }
    }

}
