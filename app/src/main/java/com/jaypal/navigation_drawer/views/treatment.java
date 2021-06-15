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
import android.widget.GridView;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.jaypal.navigation_drawer.MainActivity;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.adapter.featured_tret_adapter;
import com.jaypal.navigation_drawer.adapter.frag_treatment_recycler;
import com.jaypal.navigation_drawer.adapter.medicalAdapter;
import com.jaypal.navigation_drawer.adapter.specialistAdapter;
import com.jaypal.navigation_drawer.model.featured_tret;
import com.jaypal.navigation_drawer.model.hospital;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class treatment extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private GridView gridView;
    ShimmerFrameLayout sh;
    SearchView searchView;
    featured_tret_adapter f;
    private RecyclerView recyclerView, recyclerView2;
     frag_treatment_recycler recycler;
    List<String> list =new ArrayList<>();
    List<featured_tret> featured_trets = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treatment, container, false);
        recyclerView = view.findViewById(R.id.frag_treatment_recycler);
        recyclerView2 = view.findViewById(R.id.frag_treatment_gridview);
        searchView = view.findViewById(R.id.search_treat);
        sh=view.findViewById(R.id.shimer78);

        addData();
        getTreatments();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recycler.getFilter().filter(query);
                f.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recycler.getFilter().filter(newText);
                f.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }

    public void addData() {
        sh.startShimmer();
        db.collection("featured_tret")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                featured_tret t = document.toObject(featured_tret.class);
                                featured_trets.add(t);
                            }
                            f = new featured_tret_adapter(getContext(), featured_trets);
                            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                            recyclerView2.setAdapter(f);
                            sh.hideShimmer();

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void getTreatments() {
        sh.startShimmer();
        db.collection("treatments")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                list = (List<String>) document.get("treatment");
                                recycler = new frag_treatment_recycler(getContext(), list);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                recyclerView.setAdapter(recycler);
                                sh.stopShimmer();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}