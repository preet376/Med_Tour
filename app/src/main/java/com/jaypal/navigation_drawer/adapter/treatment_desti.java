package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.destination_detail;

import java.util.List;

import static android.content.ContentValues.TAG;

public class treatment_desti extends BaseAdapter {
    Context context;
    List<String>names;
    public treatment_desti(Context context, List<String>names) {
        this.context=context;
        this.names=names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
           // Log.d(TAG, "getView: "+"view is null");
            view= LayoutInflater.from(context).inflate(R.layout.item_desti_name,null);
        }
       // final ImageView imageView=view.findViewById(R.id.specialist_image);
        //final TextView textView=view.findViewById(R.id.treatment_desti_name);
        //imageView.setImageResource(specialistsimg[i]);
        //textView.setText(names.get(i));
        Button b=view.findViewById(R.id.treatment_desti_name);
        b.setText(names.get(i));
        //Log.d(TAG, "getView: "+names.get(i));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+names.get(i));
                Intent intent=new Intent(context, destination_detail.class);
                // Bundle bundle=new Bundle();
                //bundle.putInt("int",i);
                //intent.putExtra("bundle",bundle);
                intent.putExtra("cname",names.get(i));
                context.startActivity(intent);
            }
        });
        return view;

    }
}
