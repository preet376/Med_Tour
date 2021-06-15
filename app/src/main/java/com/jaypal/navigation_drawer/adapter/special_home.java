package com.jaypal.navigation_drawer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaypal.navigation_drawer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class special_home extends BaseAdapter {
    Context mcontext;
    ArrayList<String> special=new ArrayList<>();
   String []sp={"Phyisician","Cardiologist","Neurologist","Pulmonologist","Psychiatrist","Dentist","Gynaecologist","Orthopidician","Genetic"};
    public special_home(Context mcontext,ArrayList<String>special) {
        this.mcontext = mcontext;
        this.special=special;
    }

    //  int []specialistsimg={R.drawable.dental1,R.drawable.brain,R.drawable.flt,R.drawable.pltsur, R.drawable.wtloss};
    @Override
    public int getCount() {
        return 9;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
        {
            view= LayoutInflater.from(mcontext).inflate(R.layout.item_specialist_model,null);
        }


        final ImageView imageView=view.findViewById(R.id.item_sp_img);
        Picasso.get().load(special.get(i)).into(imageView);
        final TextView textView=view.findViewById(R.id.item_sp_name);
           textView.setText(sp[i]);
        return view;
    }
}
