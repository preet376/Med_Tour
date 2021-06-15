package com.jaypal.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaypal.navigation_drawer.adapter.medicalAdapter;
import com.jaypal.navigation_drawer.adapter.treatment_hospital;
import com.jaypal.navigation_drawer.model.hospital;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class medicine_center_details extends AppCompatActivity {
    TextView hos_name,abt_hos,abt_hos_desc,hos_location,hos_lbl;
    ImageView img;
    String cname,lname,nam;
    Toolbar toolbar;
    private static final String TAG = "Medicine_center_details";
    RecyclerView recyclerView;
    ArrayList<hospital> hospitals=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_center_details);
       // Bundle bundle=getIntent().getBundleExtra("bund");
        //if (bundle.isEmpty())
          //  Log.d(TAG, "onCreate: "+"Bundle Empty");
        //Log.d(TAG, "onCreate: "+"Bundle not Empt");
        //int position=bundle.getInt("int");
        toolbar=findViewById(R.id.toolbar42);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cname=getIntent().getStringExtra("cname");
        lname=getIntent().getStringExtra("lname");
        nam=getIntent().getStringExtra("nam");
        hos_name=findViewById(R.id.name_hospital);
        abt_hos=findViewById(R.id.about_hospital);
        hos_lbl=findViewById(R.id.hos_treatment_lbl);
        abt_hos_desc=findViewById(R.id.about_hospital_desc);
        hos_location=findViewById(R.id.location_hospital);
        img=findViewById(R.id.img_hospital);
        hospitals=MainActivity.hospitals;
        hospital h=new hospital();
        for(int t=0;t<hospitals.size();t++)
        {
            if (hospitals.get(t).getCountry_name().equals(cname)&&hospitals.get(t).getName().equals(nam)&&hospitals.get(t).getLocation().equals(lname))
            {
                h=hospitals.get(t);
                break;
            }
        }


        hos_name.setText(h.getName());
        abt_hos.setText("About "+h.getName());
        abt_hos_desc.setText(h.getAbout());
      //  if (!h.getLink().equals("link"))
        {
            Picasso.get().load(h.getLink()).into(img);
        }

        hos_location.setText(h.getLocation()+","+h.getCountry_name());
        recyclerView=findViewById(R.id.hospital_details_recycler);
        if (!(h.getList()==null)&&(!h.getList().get(0).equals("")))
        {
            treatment_hospital t=new treatment_hospital(this, (ArrayList<String>) h.getList());
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true));
            recyclerView.setAdapter(t);
        }
        else {
            hos_lbl.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}