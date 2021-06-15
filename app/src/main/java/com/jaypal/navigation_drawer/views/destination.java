package com.jaypal.navigation_drawer.views;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jaypal.navigation_drawer.R;
import com.jaypal.navigation_drawer.adapter.destination_Adapter;
import com.jaypal.navigation_drawer.adapter.recyclerAdapter;
import com.jaypal.navigation_drawer.model.country;

import java.util.ArrayList;


public class destination extends Fragment {
    ArrayList<country> countries=new ArrayList<>();
RecyclerView destination_recyclerView;
SearchView searchView;
ShimmerFrameLayout sh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_destination,container,false);
        Bundle bundle=this.getArguments();
        if(bundle!=null)
            countries=bundle.getParcelableArrayList("list_desti");
        destination_recyclerView=v.findViewById(R.id.recycler_destinations);
        sh=v.findViewById(R.id.shimmer65);
        searchView=v.findViewById(R.id.frag_disti_search);
        //recyclerAdapter adapter=new recyclerAdapter(getContext());
        final destination_Adapter adapter=new destination_Adapter(getContext(),countries);
        destination_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false) );
        destination_recyclerView.setAdapter(adapter);
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
        sh.hideShimmer();
        return v;
    }
}