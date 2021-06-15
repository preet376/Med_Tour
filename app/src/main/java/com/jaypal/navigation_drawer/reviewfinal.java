package com.jaypal.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaypal.navigation_drawer.adapter.myAdapter;
import com.jaypal.navigation_drawer.model.User;
import com.jaypal.navigation_drawer.model.user;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class reviewfinal extends AppCompatActivity implements View.OnClickListener  {
    private RatingBar rBar;
    private TextView tView;
    TextView aname,adate,areview;
    Button aupdate,adelete;
    RatingBar arating;
    private Button btn;
    private EditText result;
    Toolbar toolbar;
    List<user> rev=new ArrayList<>();
    RecyclerView recyclerView;
    EditText ren,rer;
    ImageButton imgbtn,img_popmenu;
    Button button;
    CardView cardView,ecardview;
    TextView tmyreview;
    CollectionReference collectionReference;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String sn=  FirebaseAuth.getInstance().getCurrentUser().getEmail().replaceFirst("@gmail.com","");
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewfinal);
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        n =getIntent().getStringExtra("name_hos");
        final String[] un = new String[1];
        imgbtn.setOnClickListener(this);
        img_popmenu.setOnClickListener(this);
//        aupdate.setOnClickListener(this);
//        adelete.setOnClickListener(this);
        db.collection("reviews").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                rev.clear();
                for (DocumentSnapshot doc :value)
                {
                    user u=doc.toObject(user.class);
                    if (u.getHospital().equals(n.trim()))
                    {
                        if (u.getName().equals(sn))
                        {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            String dateString = formatter.format(new Date(u.getTimestamp().toDate().toString()));
                            tmyreview.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.VISIBLE);
                            ecardview.setVisibility(View.GONE);
                            aname.setText(u.getName());
                            adate.setText(dateString);
                            arating.setRating(u.getRating());
                            areview.setText(u.getReview());
                        }else {
                            rev.add(u);
                        }
                    }
     }
                Collections.sort(rev);
                myAdapter adapter=new myAdapter(getApplicationContext(),rev,n);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false) );
                recyclerView.setAdapter(adapter);
     }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.rebtn:
                send_review();
                break;
            case R.id.popmenu:
                showPopupMenu(img_popmenu,0);
        }

    }
    private void showPopupMenu(View view,int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(reviewfinal.this, img_popmenu);
       popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
       popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem menuItem) {
               delete_review();
               return false;
           }
       });
       popup.show();
    }

    private void init()
{
    rBar = (RatingBar) findViewById(R.id.ratingBar1);
    tView = (TextView) findViewById(R.id.rereview);
    cardView=findViewById(R.id.myreview);
    ecardview=findViewById(R.id.ereview);
    tmyreview=findViewById(R.id.tmyreview);
    imgbtn = findViewById(R.id.rebtn);
   img_popmenu=findViewById(R.id.popmenu);
    result = (EditText) findViewById(R.id.add);
    recyclerView=findViewById(R.id.recyclerre);

    aname=findViewById(R.id.aname);
    arating=findViewById(R.id.arating);
    areview=findViewById(R.id.areview);
    adate=findViewById(R.id.adate);
//    aupdate=findViewById(R.id.aupdate);
//    adelete=findViewById(R.id.adelete);


    rer=findViewById(R.id.rereview);
    toolbar=findViewById(R.id.toolbar122);
}
    private void send_review() {
        final String[] str = new String[1];
        db.collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User us=documentSnapshot.toObject(User.class);
                str[0] =us.getFullname();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: "+e.toString());
            }
        });
        final user u=new user(sn, rBar.getRating(),rer.getText().toString(), Timestamp.now(),n);
        rer.setText("");
        rBar.setRating((float) 0.0);
        db.collection("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int k=0;
                for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    user m=documentSnapshot.toObject(user.class);
                    if (m.getName().equals(sn)&&m.getHospital().equals(n)){
                        k=1;
                        break;
                    }
                }
                if (k==0)
                {
                    db.collection("reviews").add(u).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(reviewfinal.this, "Thank you for review", Toast.LENGTH_SHORT).show();
                        }
                    });
                    tmyreview.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.VISIBLE);
                    ecardview.setVisibility(View.GONE);
                }else {
                    //Toast.makeText(reviewfinal.this, "you already given review", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void delete_review() {
        db.collection("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int k=0;
                for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    user m=documentSnapshot.toObject(user.class);
                    if (m.getName().equals(sn)&&m.getHospital().equals(n)){
                        db.collection("reviews").document(documentSnapshot.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Review Deleted Successfully",Toast.LENGTH_SHORT).show();
                                tmyreview.setVisibility(View.GONE);
                                cardView.setVisibility(View.GONE);
                                ecardview.setVisibility(View.VISIBLE);
                            }
                        });
                        k=1;
                        break;
                    }
                }
               }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void update_review() {
        db.collection("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                int k=0;
                for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    user m=documentSnapshot.toObject(user.class);
                    if (m.getName().equals(sn)&&m.getHospital().equals(n)){

                        ecardview.setVisibility(View.VISIBLE);
                        rBar.setRating(m.getRating());
                        rer.setText(m.getReview());
                        delete_review();
                        send_review();

                        k=1;
                        break;
                    }
                }
    }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });



    }


}

