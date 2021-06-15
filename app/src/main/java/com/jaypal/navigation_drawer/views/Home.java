package com.jaypal.navigation_drawer.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jaypal.navigation_drawer.MainActivity;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.adapter.destination_Adapter;
import com.jaypal.navigation_drawer.adapter.frag_treatment_recycler;
import com.jaypal.navigation_drawer.adapter.medicalAdapter;
import com.jaypal.navigation_drawer.adapter.recyclerAdapter;
import com.jaypal.navigation_drawer.adapter.special_home;
import com.jaypal.navigation_drawer.adapter.specialistAdapter;
import com.jaypal.navigation_drawer.comparelist;
import com.jaypal.navigation_drawer.database.database;
import com.jaypal.navigation_drawer.model.Doctor;
import com.jaypal.navigation_drawer.model.compare_tret;
import com.jaypal.navigation_drawer.model.country;
import com.jaypal.navigation_drawer.model.hospital;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;


public class Home extends Fragment {
    ArrayList<country>countries=new ArrayList<>();
ArrayList<String>special=new ArrayList<>();
    ArrayList<Doctor>Doctors=new ArrayList<>();
    ArrayList<hospital>hospitals=new ArrayList<>();
FirebaseFirestore db=FirebaseFirestore.getInstance();
FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
StorageReference storageReference=firebaseStorage.getReference();
    private static final String TAG = "Home";
    List<String> list =new ArrayList<>();
RecyclerView recyclerView,recyclerView2;
    special_home spAdapter;
GridView gridView;
CardView cardView,cardtret;
ProgressBar progressBar;
ShimmerFrameLayout sh;
Button view_desti,view_medicine;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home,container,false);




        recyclerView=view.findViewById(R.id.recycler_view);
        cardView=view.findViewById(R.id.card2);
        cardtret=view.findViewById(R.id.card_tret);
        view_desti=view.findViewById(R.id.view_destination);
        view_medicine=view.findViewById(R.id.view_medicine_centers);
        gridView =view.findViewById(R.id.gridview);
       // specialistAdapter spAdapter=new specialistAdapter(getContext());

        sh=view.findViewById(R.id.shimer);

        recyclerView2=view.findViewById(R.id.recycler_view2);
         doctors();
        getSpecial();
        insert();
        hospitals();
            if (countries.isEmpty())
                Log.d(TAG, "onCreateView: "+"Countries are Empty");
            Log.d(TAG, "onCreateView: "+"Bundle is null");
            recyclerAdapter adapter=new recyclerAdapter(getContext(),countries);
            //destination_Adapter adapter=new destination_Adapter(getContext(),countries);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false) );
            recyclerView.setAdapter(adapter);

        view_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selected_fragment=null;
                selected_fragment=new medicine_centers();
               /* Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("list_hospi", hospitals);
                selected_fragment.setArguments(bundle);*/
                getFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();


            }
        });
        view_desti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selected_fragment=null;
                selected_fragment=new destination();
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("list_desti", countries);
                selected_fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();

            }
        });
         cardView.setOnClickListener(new View.OnClickListener() {
         @Override
        public void onClick(View view) {
           getCompare();
        }
        });
        cardtret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selected_fragment=null;
                selected_fragment=new treatment();
               // Bundle bundle=new Bundle();
                //bundle.putParcelableArrayList("list_desti", countries);
                //selected_fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();
            }
        });
         gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Fragment selected_fragment=null;
                 selected_fragment=new doctors();
                 Bundle bundle=new Bundle();
                 bundle.putInt("position",i);
                 selected_fragment.setArguments(bundle);
                 getFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();

             }
         });

        return view;
    }


    private void getSpecial()
    {

        db.collection("special")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Set<country> g=new HashSet<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                special= (ArrayList<String>) document.get("sp");

                            }
                            spAdapter=new special_home(getContext(),special);
                            gridView.setAdapter(spAdapter);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void insert() {
        //progressBar.setVisibility(View.VISIBLE);

     db.collection("country")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Set<country> g=new HashSet<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                country d=document.toObject(country.class);

                                //Log.d(TAG, "onComplete: "+d.getName());
                                //Log.d(TAG, "onComplete: "+"Adding successfully");
                                g.add(d);

                            }
                                countries.addAll(g);
                            recyclerAdapter adapter=new recyclerAdapter(getContext(),countries);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false) );
                            recyclerView.setAdapter(adapter);
                           // progressBar.setVisibility(View.GONE);
                            sh.hideShimmer();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }
    private  void hospitals()
    {
       // progressBar.setVisibility(View.VISIBLE);
         String []tr1={"MHE(Multiple Hereditary Exostoses)","AVR-Aortic Valve Replacement","Acne","Adenoidectomy",
                "Addiction Treatment","Advanced Checkup","Aging Skin","Amputations"};
        List<String> list= Arrays.asList(tr1);
        final hospital hel=new hospital("100Pasos","Toledo","Spain","Hello",list,"link");
        /*for (int i=0;i<100;i++)
        {
            db.collection("hospital").add(h).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "onSuccess: "+"hospital Added Successfully");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: "+e.toString());
                }
            });

        }*/

      /*  Log.d(TAG, "hospitals: "+"inside hospitals");
      db.collection("hospital")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //country d=document.toObject(country.class);
                                hospital h=document.toObject(hospital.class);
                                //countries.add(d);
                                //hospitals.add(h);
                                Log.d(TAG, "onComplete: "+"inside db");
                                if (h.getCountry_name()==hel.getCountry_name())
                                {
                                    Log.d(TAG, "onComplete: "+"inside ==");
                                    DocumentReference documentReference=document.getReference();
                                    documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "onSuccess: "+"deleted successfully");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure: "+e.toString());
                                        }
                                    });
                                }
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });*/
        db.collection("hospital")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //country d=document.toObject(country.class);
                                hospital h=document.toObject(hospital.class);
                                //countries.add(d);

                                if((!h.getAbout().equals("Hello"))&&(!h.getLocation().equals("Toledo"))&&(!h.getLink().equals("link")))
                                {
                                    i++;
                                    hospitals.add(h);
                                 //   Log.d(TAG, "mynameis "+h.getCountry_name()+i);
                                }
                                //    Log.d(TAG, document.getId() + " => " + document.getData());

                            }
                            medicalAdapter madapter=new medicalAdapter(getContext(),hospitals);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false) );
                            recyclerView2.setAdapter(madapter);
                          //  progressBar.setVisibility(View.GONE);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


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
       /* db.collection("doctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Doctor d=document.toObject(Doctor.class);
                                Doctors.add(d);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });*/



    }
    public void getCompare()
    {
        Intent intent=new Intent(getContext(), comparelist.class);
        getContext().startActivity(intent);



//        String []tname={"Angioplasty","Heart Bypass","Heart Valve Replacement","Hip Replacement","Hip Resurfacing","Knee Replacement","Spinal Fusion","Dental Implant","Lap Band",
//      "Gastric Sleeve", "Gastric Bypass","Hysterectomy", "Breast Implants","Rhinoplasty","Face Lift","Liposuction","Tummy Tuck","Lasik (both eyes)","Cornea (per eye)",
//        "Cataract surgery (per eye)","IVF Treatment"};
//        String[]cname={"Colombia","Costa Rica","India","Israel","Jordan","Malaysia","Mexico","Poland","Singapore","S. Korea","Thailand","Turkey","USA","Vietnam"};
//        List<String>com=Arrays.asList(cname);
//        String []price={"","","","","","","","","","","","","",""};
//        List<String>pri=Arrays.asList(price);
      /*  for (int n=0;n<tname.length;n++)
        {
            compare_tret tr=new compare_tret(tname[n],com,pri);
            db.collection("compare").add(tr).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d(TAG, "onSuccess: "+"compare Added Successfully");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: "+e.toString());
                }
            });
        }*/
    }




}