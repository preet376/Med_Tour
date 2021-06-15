package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaypal.navigation_drawer.MainActivity;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.tratement_details;

import java.util.ArrayList;
import java.util.List;

public class frag_treatment_recycler extends RecyclerView.Adapter<frag_treatment_recycler.frag_holder> implements Filterable {
    Context context;
    List<String> list;
    List<String> tlist;
    public frag_treatment_recycler(Context context,   List<String> list) {
        this.context = context;
        this.list=list;
        this.tlist=list;
    }

    @NonNull
    @Override
    public frag_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_treatment_hospital,parent,false);
        return new frag_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull frag_holder holder, final int position) {
        holder.textView.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, tratement_details.class);
                intent.putExtra("tname",list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString=charSequence.toString();
                if (charString.isEmpty())
                {
                    list=tlist;
                }
                else {
                    List<String>d=new ArrayList<>();
                    for (String f :tlist)
                    {
                        if (f.toLowerCase().trim().contains(charString.toLowerCase().trim()))
                            d.add(f);
                    }
                    list=d;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list=(List<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class frag_holder extends RecyclerView.ViewHolder {
        TextView textView;
        public frag_holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.treatment_name);
        }
    }
}
