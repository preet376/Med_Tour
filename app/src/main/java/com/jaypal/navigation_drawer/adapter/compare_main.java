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
import com.jaypal.navigation_drawer.comapare_final;

import java.util.List;

public class compare_main  extends RecyclerView.Adapter<compare_main.compareholdermain> {
    Context context;
    List<String>clist;

    public compare_main(Context context, List<String> clist) {
        this.context = context;
        this.clist = clist;
    }

    @NonNull
    @Override
    public compareholdermain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_compare_main,parent,false);
        return new compare_main.compareholdermain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull compareholdermain holder, final int position) {
           holder.textView.setText(clist.get(position));
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent(context,comapare_final.class);
                   intent.putExtra("rname",clist.get(position));
                   context.startActivity(intent);
               }
           });
    }

    @Override
    public int getItemCount() {
        return clist.size();
    }

    public class compareholdermain extends RecyclerView.ViewHolder {
        TextView textView;
        public compareholdermain(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.cmain_name);

        }
    }
}
