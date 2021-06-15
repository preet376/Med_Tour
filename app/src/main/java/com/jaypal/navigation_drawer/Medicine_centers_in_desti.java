package com.jaypal.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jaypal.navigation_drawer.adapter.frag_treatment_recycler;
import com.jaypal.navigation_drawer.adapter.medicalAdapter_2;
import com.jaypal.navigation_drawer.model.hospital;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Medicine_centers_in_desti extends AppCompatActivity {
    RecyclerView hos,tret;
    Toolbar toolbar;
    ArrayList<hospital> hospitals=new ArrayList<>();
    ArrayList<hospital>h=new ArrayList<>();
    Set<String>set=new TreeSet<>();
    ArrayList<String>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_centers_in_desti);
        toolbar=findViewById(R.id.toolbar85);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        hos=findViewById(R.id.desti_hospitals_list);
        tret=findViewById(R.id.desti_treatment_list);
        hospitals=MainActivity.hospitals;
        String str=getIntent().getStringExtra("cname");
        for (int i=0;i<hospitals.size();i++)
        {
            //Log.d(TAG, "onCreate: "+i);
            if (hospitals.get(i).getCountry_name().equals(str))
            {
                //if (hospitals.get(i).getList().contains(tr))
                {
                    h.add(hospitals.get(i));
                    if ((hospitals.get(i).getList()!=null)&&(hospitals.get(i).getList().get(0)!=""))
                         set.addAll(hospitals.get(i).getList());
                    //Log.d(TAG, "onCreate: "+hospitals.get(i).getName());

                }
            }

        }
        list.addAll(set);
        medicalAdapter_2 madapter=new medicalAdapter_2(this,h);
        hos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) );
        hos.setAdapter(madapter);
        frag_treatment_recycler recycler=new frag_treatment_recycler(Medicine_centers_in_desti.this,list);

        tret.setLayoutManager(new LinearLayoutManager(Medicine_centers_in_desti.this,LinearLayoutManager.VERTICAL,false));
        tret.setAdapter(recycler);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}