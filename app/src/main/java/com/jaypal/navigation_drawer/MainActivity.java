package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaypal.navigation_drawer.model.Doctor;
import com.jaypal.navigation_drawer.model.country;
import com.jaypal.navigation_drawer.model.hospital;
import com.jaypal.navigation_drawer.views.Home;
import com.jaypal.navigation_drawer.views.destination;
import com.jaypal.navigation_drawer.views.doctors;
import com.jaypal.navigation_drawer.views.medicine_centers;
import com.jaypal.navigation_drawer.views.treatment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  public static   ArrayList<country> countries=new ArrayList<>();
    ArrayList<Doctor>Doctors=new ArrayList<>();
    public  static List<String>list=new ArrayList<>();
   public static ArrayList<hospital>hospitals=new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage= FirebaseStorage.getInstance();
    StorageReference storageReference=firebaseStorage.getReference();
    ProgressBar progressBar;
private DrawerLayout mnavdrawer;
public static BottomNavigationView bottomNavigationView;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_layout);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mnavdrawer=findViewById(R.id.drawer);
        countries=getCountries();
        bottomNavigationView=findViewById(R.id.bottom_nav);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mnavdrawer,toolbar,R.string.nav_Drawer_Open,R.string.nav_Drawer_close);
        mnavdrawer.addDrawerListener(toggle);
        NavigationView navigationView=findViewById(R.id.navigationview);
        toggle.syncState();
        countries=getCountries();
        hospitals=gethospitals();
        //getTreatments();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState==null)
        {
            Fragment selected_fragment=null;
            selected_fragment=new Home();
            Bundle bundle=new Bundle();
            bundle.putParcelableArrayList("list_desti", countries);
            selected_fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }

        bottomNavigationView.setItemIconSize(80);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected_fragment=null;
                switch(item.getItemId())
                {
                    case R.id.nav_home :
                        selected_fragment=new Home();
                        Bundle bundle=new Bundle();
                        bundle.putParcelableArrayList("list_desti", countries);
                        selected_fragment.setArguments(bundle);
                               break;
                    case R.id.nav_destination :
                        selected_fragment=new destination();
                         bundle=new Bundle();
                        bundle.putParcelableArrayList("list_desti", countries);
                        selected_fragment.setArguments(bundle);
                        break;
                    case R.id.nav_treatment :
                        selected_fragment=new treatment();
                        break;
                    case R.id.nav_doctors :
                        selected_fragment=new doctors();
                        break;
                    case R.id.nav_medicine_centers :
                        selected_fragment=new medicine_centers();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,selected_fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {


        if (mnavdrawer.isDrawerOpen(GravityCompat.START))
        {
            mnavdrawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            if (doubleBackToExitPressedOnce) {
                //super.onBackPressed();
                finishAffinity();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "press again to exit application", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
           case R.id.nav_logout :
               FirebaseAuth.getInstance().signOut();
               FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

               if (user == null) {
                   // User is signed in
                   Intent i = new Intent(MainActivity.this, login.class);
                   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(i);
               } else {
                   // User is signed out
                   Log.d((String) TAG, "onAuthStateChanged:signed_out");
               }
             //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new BlankFragment()).commit();
                break;
            case R.id.nav_emailverify:
                    verify();

         /*   case R.id.outbox :
                Toast.makeText(this,"outbox is open",Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new outboxfragment()).commit();
                break;*/
        }
        mnavdrawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public ArrayList<country>  getCountries()
    {
       // progressBar.setVisibility(View.VISIBLE);
        db.collection("country")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Set<country> g=new HashSet<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                country d=document.toObject(country.class);
                              //  Log.d(TAG, "onComplete: "+d.getName());
                                //Log.d(TAG, "onComplete: "+"Adding successfully");
                                //countries.add(d);
                                g.add(d);
                            }
                            countries.addAll(g);
                           /* for (int i = 0; i < countries.size(); i++) {
                                Log.d(TAG, "insert: "+"country "+i);
                                Log.d(TAG, "insert: "+countries.get(i).getCapital());

                            }*/
                          /*  recyclerAdapter adapter=new recyclerAdapter(getContext(),countries);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false) );
                            recyclerView.setAdapter(adapter);*/
                          //  progressBar.setVisibility(View.GONE);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return countries;
    }
    public ArrayList<hospital> gethospitals()
    {
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

                                if((!h.getAbout().equals("Hello")))
                                {
                                    i++;
                                    hospitals.add(h);
                                   // Log.d(TAG, "mynameis "+h.getCountry_name()+i);
                                }
                                //    Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return hospitals;
    }

void verify()
{
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
 if (!user.isEmailVerified())
 {
     user.sendEmailVerification()
             .addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if (task.isSuccessful()) {
                         Log.d(TAG, "Email sent.");
                         Toast.makeText(MainActivity.this,"Email Sent",Toast.LENGTH_SHORT).show();
                     }
                 }
             });
 }
   else {
     Toast.makeText(MainActivity.this,"Email Already verified",Toast.LENGTH_SHORT).show();
 }


}
    }

