package com.jaypal.navigation_drawer.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.adapter.frag_doctor_recycler;
import com.jaypal.navigation_drawer.model.Doctor;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class doctors extends Fragment {
    ArrayList<Doctor> Doctors=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    int position=-1;
    frag_doctor_recycler adapter;
    SearchView searchView;
    StorageReference storageReference=firebaseStorage.getReference();
RecyclerView recyclerView;
    String []sp={"Phyisician","Cardiologist","Neurologist","Pulmonologist","Psychiatrist","Dentist","Gynaecologist","Orthopidician","Genetic"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_doctors,container,false);
        recyclerView=view.findViewById(R.id.frag_doctor_recycler);
        searchView=view.findViewById(R.id.frag_doctor_search);
        Bundle bundle=this.getArguments();
        if(bundle!=null)
           position =bundle.getInt("position");
        doctors();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        if (position!=-1)
        {
            searchView.setQuery(sp[position],true);
            //adapter.getFilter().filter(sp[position]);



        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }
    private void doctors()
    {

       db.collection("doctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Doctor d=document.toObject(Doctor.class);
                                Doctors.add(d);
                             //   Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            adapter=new frag_doctor_recycler(getContext(),Doctors);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



    }
}