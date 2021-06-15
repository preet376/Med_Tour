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
import com.jaypal.navigation_drawer.model.country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.myHolder> {
    Context context;
    ArrayList<country>countries;

    public recyclerAdapter(Context context,ArrayList<country>countries) {
        this.context = context; this.countries=countries;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.destination_model,parent,false);
        return new myHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
         //holder.name_team.setText(list.get(position));
       // holder.imageView.setImageResource(images[position]);
       final int i=position;
        Picasso.get().load(countries.get(position).getLink()).into(holder.imageView);
        holder.textView.setText(countries.get(position).getName());
        holder.description.setText(countries.get(position).getInshort());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, destination_detail.class);
               // Bundle bundle=new Bundle();
                //bundle.putInt("int",i);
                //intent.putExtra("bundle",bundle);
                intent.putExtra("cname",countries.get(i).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView description;
        ImageView imageView;
        TextView textView;
        public myHolder(@NonNull View view) {

            super(view);
            imageView = view.findViewById(R.id.imageview_country);

            //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
           description=view.findViewById(R.id.description_country);
           textView = view.findViewById(R.id.name_country);

        }
    }
}
