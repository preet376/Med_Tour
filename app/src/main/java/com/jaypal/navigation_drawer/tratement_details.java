package com.jaypal.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.jaypal.navigation_drawer.adapter.frag_treatment_recycler;
import com.jaypal.navigation_drawer.adapter.medicalAdapter_2;
import com.jaypal.navigation_drawer.adapter.treatment_desti;
import com.jaypal.navigation_drawer.model.Doctor;
import com.jaypal.navigation_drawer.model.country;
import com.jaypal.navigation_drawer.model.hospital;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class tratement_details extends AppCompatActivity {
    private static final String TAG = "Treatment_details";
    RecyclerView desti,provider;
TextView tretname;
GridView gridView;
Toolbar toolbar;
    ArrayList<country> countries=new ArrayList<>();
    ArrayList<String> c=new ArrayList<>();
    ArrayList<Doctor>Doctors=new ArrayList<>();
    ArrayList<hospital>hospitals=new ArrayList<>();
    ArrayList<hospital>h=new ArrayList<>();
    Set<String> set=new HashSet<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratement_details);
       // desti=findViewById(R.id.tratement_destinations);
        toolbar=findViewById(R.id.toolbar95);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        provider=findViewById(R.id.tratement_hospitals);
        tretname=findViewById(R.id.tretname);
        gridView=findViewById(R.id.treatment_grid_desti_name);
        countries=MainActivity.countries;
        hospitals=MainActivity.hospitals;
        String tr=getIntent().getStringExtra("tname");
        Log.d(TAG, "onCreate: "+tr);
        tretname.setText(tr);
        Log.d(TAG, "onCreate: "+"Inside tratement_details");
        for (int i=0;i<hospitals.size();i++)
        {
            //Log.d(TAG, "onCreate: "+i);
            if ((hospitals.get(i).getList()!=null)&&(!hospitals.get(i).getList().get(0).equals("")))
            {
                for (int j=0;j<hospitals.get(i).getList().size();j++)
                {
                    if (hospitals.get(i).getList().get(j).toLowerCase().equals(tr.toLowerCase()))
                    {
                        h.add(hospitals.get(i));
                        //Log.d(TAG, "onCreate: "+hospitals.get(i).getName());

                    }
                }

            }

        }
        for (int j=0;j<countries.size();j++)
        {
            for (int i=0;i<h.size();i++)
            {

               if (countries.get(j).getName().equals(h.get(i).getCountry_name()))
               {
                   set.add(countries.get(j).getName());
                   //Log.d(TAG, "onCreate: "+h.get(i).getCountry_name());
               }
            }

        }
        c.addAll(set);
        frag_treatment_recycler recycler=new frag_treatment_recycler(this,c);

    /*    desti.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        desti.setAdapter(recycler);*/
        treatment_desti grid=new treatment_desti(this,c);
        gridView.setAdapter(grid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: "+c.get(i));
            }
        });
        medicalAdapter_2 madapter=new medicalAdapter_2(this,h);
        provider.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) );
        provider.setAdapter(madapter);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}