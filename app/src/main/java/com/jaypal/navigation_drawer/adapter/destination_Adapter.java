package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.destination_detail;
import com.jaypal.navigation_drawer.model.country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class destination_Adapter extends RecyclerView.Adapter<destination_Adapter.myHolder2> implements Filterable {
    ArrayList<country> countries;
    Context context;
    List<country>filtered;
    int lastposition=-1;
    public destination_Adapter(Context context, ArrayList<country> countries) {
        this.context = context;
        this.countries=countries;
        this.filtered=countries;
    }

    @NonNull
    @Override
    public myHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.destinations_model_2,parent,false);
        return new myHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder2 holder, final int position) {
            holder.sh.hideShimmer();
            Picasso.get().load(filtered.get(position).getLink()).into(holder.imageView);
            holder.textView.setText(filtered.get(position).getName());
            holder.description.setText(filtered.get(position).getInshort());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, destination_detail.class);
                    intent.putExtra("cname",filtered.get(position).getName());
                    context.startActivity(intent);
                }
            });
    //    }

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
                    filtered=countries;
                }
                else {
                    List<country>f=new ArrayList<>();
                    Set<country>m=new HashSet<>();
                    for (country x :countries)
                    {
                        if (x.getName().toLowerCase().trim().contains(charString.toLowerCase().trim()))
                        {
                            if (f.size()>0)
                            {
                                for (int i=0;i<f.size();i++)
                                {
                                    if (f.get(i).getName().toLowerCase().trim().contains(x.getName().toLowerCase().trim()))
                                    {

                                    }
                                    else
                                    {
                                        f.add(x);
                                    }
                                }
                            }
                            else
                            {
                                f.add(x);
                            }

                       //   m.add(x);


                            Log.d(TAG, "performFiltering: "+"it comes more times"+x.getName());
                        }
                    }
                    //f.addAll(m);
                  // filtered.clear();
                    filtered=f;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
               filtered=(ArrayList<country>)filterResults.values;
               notifyDataSetChanged();
            }
        };
    }

    public class myHolder2 extends RecyclerView.ViewHolder {
        TextView description;
        ImageView imageView;
        TextView textView;
        ShimmerFrameLayout sh;
        public myHolder2(@NonNull View view) {

            super(view);
            imageView = view.findViewById(R.id.imageview_country2);

            //imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            textView = view.findViewById(R.id.name_country2);
            description=view.findViewById(R.id.description);
            sh=view.findViewById(R.id.shemaro21);
        }

    }
}
