package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.model.compare_tret;

import java.util.List;

import static android.content.ContentValues.TAG;

public class compare_count extends RecyclerView.Adapter<compare_count.compareholder> {
    Context context;
    List<String> clist;
    List<String> plist;

    public compare_count(Context context, List<String> clist,List<String> plist) {
        this.context = context;
        this.clist = clist;
        this.plist=plist;
    }

    @NonNull
    @Override
    public compareholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_compare,parent,false);
        return new compareholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull compareholder holder, int position) {
        holder.txt1.setText(clist.get(position));
        holder.txt2.setText(plist.get(position));
        Log.d(TAG, "onBindViewHolder: "+plist.get(position));
        Log.d(TAG, "onBindViewHolder: "+clist.get(position));
    }

    @Override
    public int getItemCount() {
        return clist.size();
    }

    public class compareholder extends RecyclerView.ViewHolder {
        TextView txt1,txt2;
        public compareholder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.ctext1);
            txt2=itemView.findViewById(R.id.ctext2);
        }
    }
}
