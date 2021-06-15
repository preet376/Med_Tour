package com.jaypal.navigation_drawer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jaypal.navigation_drawer.model.country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class destination_detail extends AppCompatActivity {
    private static final String TAG = "Destination Detail";
    TextView name,rank,abt,abt_desc,currency,capital,language,time,whether;
    ImageView img;
    Toolbar toolbar;
    country cd;
    Button btn;
    ArrayList<country> countries=MainActivity.countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinction_detail);
     // Bundle bundle=getIntent().getBundleExtra("bundle");
       // if (bundle.isEmpty())
         //   Log.d(TAG, "onCreate: "+"Bundle Empty");
        Log.d(TAG, "onCreate: "+"Bundle not Empt");
       //int position=bundle.getInt("int");
        String str=getIntent().getStringExtra("cname");
        for (int i=0;i<countries.size();i++)
        {
            if (countries.get(i).getName().equals(str))
            {
              cd =countries.get(i);
            }

        }

        final country c=cd;
        Log.d(TAG, "onCreate: "+c.getCapital());
        toolbar=findViewById(R.id.toolbar12);
        name=findViewById(R.id.country_name);
        rank=findViewById(R.id.country_rank);
        abt=findViewById(R.id.country_about);
        img=findViewById(R.id.country_img);
        abt_desc=findViewById(R.id.country_about_desc);
        currency=findViewById(R.id.country_currency);
        capital=findViewById(R.id.country_capital);
        language=findViewById(R.id.country_language);
        time=findViewById(R.id.country_time);
        whether=findViewById(R.id.country_whether);
        btn=findViewById(R.id.show_medicine_centers);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       name.setText(c.getName());
        rank.setText("Global Ranking  "+String.valueOf(c.getRank())+"  out of 46 Distinations");
        abt.setText("About  "+c.getName());
        abt_desc.setText(c.getAbout());
        currency.setText(c.getCurrency());
        capital.setText(c.getCapital());
        language.setText(c.getLanguage());
        time.setText(c.getTimezone());
        whether.setText(c.getWhether());
        Picasso.get().load(c.getLink()).into(img);
        btn.setText("Medical Centers in "+c.getName());
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(destination_detail.this,Medicine_centers_in_desti.class);
               intent.putExtra("cname",c.getName());
               startActivity(intent);
           }
       });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
