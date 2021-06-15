package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.model.featured_tret;
import com.jaypal.navigation_drawer.tratement_details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class featured_tret_adapter extends RecyclerView.Adapter<featured_tret_adapter.featured_holder> implements Filterable{
    Context context;
    List<featured_tret>featured_trets;
    List<featured_tret>frt;
    int lastposion=-1;
   public featured_tret_adapter(Context context, List<featured_tret>featured_trets) {
        this.context = context;
        this.featured_trets=featured_trets;
        frt=featured_trets;
    }

    @NonNull
    @Override
    public featured_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.specialist_model,parent,false);
        return new featured_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final featured_holder holder, final int position) {
          if(holder.getAdapterPosition()>lastposion)
          {
              Animation animation= AnimationUtils.loadAnimation(context,R.anim.row_in);
              animation.setDuration(250);
              holder.itemView.startAnimation(animation);
              Picasso.get().load(featured_trets.get(position).getLink()).into(holder.imageView);
              holder.sh.hideShimmer();
              holder. textView.setText(featured_trets.get(position).getTitle());
              holder.btn1.setText(featured_trets.get(position).getTret1());
              holder.btn2.setText(featured_trets.get(position).getTret2());
              lastposion=holder.getAdapterPosition();

              holder.btn1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intent=new Intent(context, tratement_details.class);
                      intent.putExtra("tname",featured_trets.get(position).getTret1());
                      context.startActivity(intent);
                  }
              });
              holder.btn2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intent=new Intent(context, tratement_details.class);
                      intent.putExtra("tname",featured_trets.get(position).getTret2());
                      context.startActivity(intent);
                  }
              });

          }
        }

    @Override
    public int getItemCount() {
        return featured_trets.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();
                if (charString.isEmpty())
                {
                 featured_trets=frt;
                }
                else {
                    List<featured_tret>l=new ArrayList<>();
                    for (featured_tret v :featured_trets)
                    {
                        if (v.getTret1().toLowerCase().trim().contains(charString.toLowerCase().trim()))
                        {
                            l.add(v);
                        }
                    }
                    featured_trets=l;

                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=featured_trets;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                 featured_trets=(ArrayList<featured_tret>)filterResults.values;
            }
        };
    }


    public class featured_holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button btn1,btn2;
        TextView textView;
        ShimmerFrameLayout sh;
        public featured_holder(@NonNull View view) {
            super(view);
             imageView=view.findViewById(R.id.specialist_image);
              btn1=view.findViewById(R.id.btn12);
              btn2=view.findViewById(R.id.btn13);
              textView=view.findViewById(R.id.name_specialist);
              sh=view.findViewById(R.id.shimmer45);

        }
    }
}
