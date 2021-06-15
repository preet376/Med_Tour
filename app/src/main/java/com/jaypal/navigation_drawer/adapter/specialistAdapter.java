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

public class specialistAdapter extends BaseAdapter {
   //
    String[]name_Sp={"Dentistry","Cosmetic Surgery","SURROGACY","NeuroLOGY","WEIGHT LOSS SURGERY"};
    Context mcontext;

    public specialistAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return name_Sp.length;
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
            view= LayoutInflater.from(mcontext).inflate(R.layout.specialist_model,null);
        }


        final ImageView imageView=view.findViewById(R.id.specialist_image);
       final Button btn1=view.findViewById(R.id.btn12);
       final Button btn2=view.findViewById(R.id.btn13);
        final TextView textView=view.findViewById(R.id.name_specialist);
      //  imageView.setImageResource(specialistsimg[i]);
        textView.setText(name_Sp[i]);
        return view;
    }
}
