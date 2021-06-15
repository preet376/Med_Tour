package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.jaypal.navigation_drawer.adapter.compare_count;
import com.jaypal.navigation_drawer.model.compare_tret;

import java.util.ArrayList;

public class comapare_final extends AppCompatActivity {
    private static final String TAG = "comaprefinal";
    RecyclerView recyclerView;
    String rname="";
    ShimmerFrameLayout sh;
    ArrayList<compare_tret> list=new ArrayList<compare_tret>();
    ArrayList<String>clist=new ArrayList<>();
    compare_count c;
    TextView txt;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comapare_final);
        rname=getIntent().getStringExtra("rname");
        txt=findViewById(R.id.comname);
        sh=findViewById(R.id.shimer15);
        txt.setText(rname);
        recyclerView=findViewById(R.id.compare_recycler_final);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) );
        getCompare();
    }
    public void getCompare()
    {
        db.collection("compare")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=0;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                compare_tret q=document.toObject(compare_tret.class);
                                if (rname.toString().toLowerCase().trim().equals(q.getName().toString().toLowerCase().trim()))
                                {
                                    c=new compare_count(comapare_final.this,q.getCname(),q.getPrice());
                                    recyclerView.setAdapter(c);
                                    sh.hideShimmer();
                                    break;
                                }
                                //list.add(q);
                                Log.d(TAG, "onComplete: "+q.getPrice());
                            }

                            //c=new compare_count(comapare_final.this,list.get(10).getCname(),list.get(10).getPrice());



                            //Log.d(TAG, "onComplete: "+list.get(10).getPrice());
                            //Log.d(TAG, "onComplete: "+list.get(10).getPrice().get(10));
                        } else {
                            Log.d(TAG, "Error getting documents.", task.getException());
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