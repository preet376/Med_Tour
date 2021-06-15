package com.jaypal.navigation_drawer.views;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jaypal.navigation_drawer.MainActivity;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.adapter.medicalAdapter;
import com.jaypal.navigation_drawer.adapter.medicalAdapter_2;
import com.jaypal.navigation_drawer.model.hospital;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class medicine_centers extends Fragment {
RecyclerView recyclerView;
SearchView searchView;
ShimmerFrameLayout sh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<hospital> hospitals=new ArrayList<>();
        hospitals= MainActivity.hospitals;
               View view=inflater.inflate(R.layout.fragment_medicine_centers,container,false);
               recyclerView=view.findViewById(R.id.recycler_medicine_centers);
               searchView=view.findViewById(R.id.frag_medi_center_search);
               sh=view.findViewById(R.id.shimmer85);
         final medicalAdapter_2 madapter=new medicalAdapter_2(getContext(),hospitals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false) );
        recyclerView.setAdapter(madapter);
        sh.hideShimmer();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                madapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                madapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }
}