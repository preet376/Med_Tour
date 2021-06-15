package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.jaypal.navigation_drawer.adapter.compare_count;
import com.jaypal.navigation_drawer.adapter.compare_main;
import com.jaypal.navigation_drawer.adapter.medicalAdapter;
import com.jaypal.navigation_drawer.model.compare_tret;
import com.jaypal.navigation_drawer.model.hospital;
import com.jaypal.navigation_drawer.views.Home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class comparelist extends AppCompatActivity {
    private static final String TAG ="Compare List" ;
    RecyclerView recyclerView;
    ShimmerFrameLayout sh;
    private DrawerLayout mnavdrawer;
    ArrayList<compare_tret>list=new ArrayList<compare_tret>();
    ArrayList<String>clist=new ArrayList<>();
compare_main c;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparelist);


       sh=findViewById(R.id.shimer14);
        recyclerView=findViewById(R.id.compare_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) );
        getCompare();
//       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                compare_tret b=new compare_tret();
//                for (compare_tret v : list)
//                {
//                    if (item.getTitle().toString().toLowerCase().trim().equals(v.getName().toLowerCase().trim()))
//                    {
//                        b.setPrice(v.getPrice());
//                        b.setCname(v.getCname());
//                        b.setName(v.getName());
//                        break;
//                    }
//                }
//                textView.setText(b.getName());
//                c=new compare_count(comparelist.this,b.getCname(),b.getPrice());
//                recyclerView.setAdapter(c);
//                mnavdrawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
      /*  if (savedInstanceState==null)
        {
            Fragment selected_fragment=null;
            selected_fragment=new Home();
         //   Bundle bundle=new Bundle();
           // bundle.putParcelableArrayList("list_desti", countries);
            //selected_fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }*/
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
                               list.add(q);
                               clist.add(q.getName());
                                Log.d(TAG, "onComplete: "+q.getPrice());
                            }
                         c=new compare_main(comparelist.this,clist);
                            recyclerView.setAdapter(c);
                            sh.hideShimmer();
                            Log.d(TAG, "onComplete: "+list.get(10).getPrice());
                            Log.d(TAG, "onComplete: "+list.get(10).getPrice().get(10));
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