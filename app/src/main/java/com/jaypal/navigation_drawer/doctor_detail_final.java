package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.jaypal.navigation_drawer.adapter.frag_doctor_recycler;
import com.jaypal.navigation_drawer.model.Doctor;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class doctor_detail_final extends AppCompatActivity {
String str="";
Doctor f;
Button btn;
    Toolbar toolbar;
TextView name,edumbbs,edumd,sp,hosname,lang,add;
TextView txt0,txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    ArrayList<Doctor> Doctors=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail_final);
        str=getIntent().getStringExtra("vname");
        name=findViewById(R.id.final_doctor_name);
       txt0=findViewById(R.id.final_txt_0);
        txt1=findViewById(R.id.final_txt_1);
        txt2=findViewById(R.id.final_txt_2);
        txt3=findViewById(R.id.final_txt_3);
        txt4=findViewById(R.id.final_txt_4);
        txt5=findViewById(R.id.final_txt_5);
        txt6=findViewById(R.id.final_txt_6);
        txt7=findViewById(R.id.final_txt_7);
        btn=findViewById(R.id.final_doctor_exp);
        sp=findViewById(R.id.final_doctor_sp);
        hosname=findViewById(R.id.final_doctor_addr);
        lang=findViewById(R.id.final_doctor_lang);
        add=findViewById(R.id.final_doctor_additional);
        toolbar=findViewById(R.id.toolbar57);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
         doctors();
    }
    private void doctors()
    {
          /*for (int n=0;n<30;n++)
          {
              Doctor d=new Doctor("Dr. Prashant","100Pasos","Toledo,Spain","16 yrs Experiance",240,"MBBS MD","Physician", (float) 4.5,"Hello");
              db.collection("doctor").add(d).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                  @Override
                  public void onSuccess(DocumentReference documentReference) {
                      Log.d(TAG, "onSuccess: "+"doctor Added Successfully");
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Log.d(TAG, "onFailure: "+e.toString());
                  }
              });
          }
*/
        db.collection("doctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Doctor d=document.toObject(Doctor.class);
                                if (str.toLowerCase().trim().equals(d.getName().toLowerCase().trim()))
                                {
                                    String l="";
                                       if (d.getLang()!=null)
                                    for (int i=0;i<d.getLang().size();i++)
                                        l+=d.getLang().get(i)+"\n";
                                    name.setText(d.getName());
                                    btn.setText(d.getExperience());
                                    sp.setText(d.getSpeciality()+" "+"in");
                                    add.setText(d.getSpeciality());
                                    hosname.setText(d.getHospital_name()+","+d.getLocation());
                                    lang.setText(l);
                                    if (d.getEdu().get(0)!=null)
                                    {
                                        txt0.setText(d.getEdu().get(0));

                                    }
                                    if (d.getEdu().get(1)!=null)
                                    {
                                        txt1.setText(d.getEdu().get(1));


                                    }
                                    if (2<d.getEdu().size())
                                    if (d.getEdu().get(2)!=null)
                                    {
                                        txt2.setText(d.getEdu().get(2));
                                        txt2.setVisibility(View.VISIBLE);
                                    }
                                    if (3<d.getEdu().size())
                                    if (d.getEdu().get(3)!=null)
                                    {
                                        txt3.setText(d.getEdu().get(3));
                                        txt3.setVisibility(View.VISIBLE);
                                    }
                                    if (4<d.getEdu().size())
                                    if (d.getEdu().get(4)!=null)
                                    {
                                        txt4.setText(d.getEdu().get(4));
                                        txt4.setVisibility(View.VISIBLE);
                                    }
                                    if (5<d.getEdu().size())
                                    if (d.getEdu().get(5)!=null)
                                    {
                                        txt5.setText(d.getEdu().get(5));
                                        txt5.setVisibility(View.VISIBLE);
                                    }
                                    if (6<d.getEdu().size())
                                    if (d.getEdu().get(6)!=null)
                                    {
                                        txt6.setText(d.getEdu().get(6));
                                        txt6.setVisibility(View.VISIBLE);
                                    }
                                    if (7<d.getEdu().size())
                                    if (d.getEdu().get(7)!=null)
                                    {
                                        txt7.setText(d.getEdu().get(7));
                                        txt7.setVisibility(View.VISIBLE);

                                    }


                                    Log.d(TAG, "onComplete: "+"got it");
                                    break;
                                }
                                Log.d(TAG, "onComplete: "+"not getting");
                                Doctors.add(d);
                               // Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            //frag_doctor_recycler adapter=new frag_doctor_recycler(getContext(),Doctors);
                            //recyclerView.setAdapter(adapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}