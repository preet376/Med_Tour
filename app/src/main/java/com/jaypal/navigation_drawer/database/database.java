package com.jaypal.navigation_drawer.database;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaypal.navigation_drawer.adapter.recyclerAdapter;
import com.jaypal.navigation_drawer.model.Doctor;
import com.jaypal.navigation_drawer.model.country;
import com.jaypal.navigation_drawer.model.hospital;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class database {
    ArrayList<country> countries=new ArrayList<>();
    ArrayList<Doctor>Doctors=new ArrayList<>();
    ArrayList<hospital>hospitals=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    StorageReference storageReference=firebaseStorage.getReference();
    public ArrayList<country>  getCountries()
    {
        db.collection("country")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                country d=document.toObject(country.class);
                                Log.d(TAG, "onComplete: "+d.getName());
                                Log.d(TAG, "onComplete: "+"Adding successfully");
                                countries.add(d);

                            }
                            for (int i = 0; i < countries.size(); i++) {
                                Log.d(TAG, "insert: "+"country "+i);
                                Log.d(TAG, "insert: "+countries.get(i).getCapital());

                            }
                          /*  recyclerAdapter adapter=new recyclerAdapter(getContext(),countries);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false) );
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);*/
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return countries;
    }
}
