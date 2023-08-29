package com.example.login;


import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import android.widget.Toast;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;




public class recfragment extends Fragment {


    RecyclerView recview;
    myadapter adapter;

    //*toolbar
    Toolbar toolbar;

    /* Search bar */
    private MenuItem menuItem;
    private SearchView searchView;



   /* public recfragment() {
        // Required empty public constructor
    }


    public static recfragment newInstance(String param1, String param2) {
        recfragment fragment = new recfragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recfragment, container, false);

        toolbar = view.findViewById(R.id.toolbar);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(" ");


        recview = (RecyclerView) view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddPG"), model.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);


        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        try {


            inflater.inflate(R.menu.searchmenu, menu);
            menuItem = menu.findItem(R.id.search);
           searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
            //SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setQueryHint("Type here to search......");
            searchView.setIconified(true);


            SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
            {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mysearch(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    mysearch(query);
                    return true;
                }
            });
            super.onCreateOptionsMenu(menu, inflater);
        }
        catch (Exception e) {
           // e.printStackTrace();
            Toast.makeText(getActivity(),"Error"+e.toString(), LENGTH_LONG).show();


        }
   }



    private void mysearch(String query) {
      //  try {

            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("AddPG").orderByChild("location").startAt(query).endAt(query + "\uf8ff"), model.class)
                            .build();

            adapter = new myadapter(options);
            adapter.startListening();
            recview.setAdapter(adapter);

        }/* catch (Exception e) {
            //e.printStackTrace();
           //Toast.makeText(this,"Error...", Toast.LENGTH_LONG).show();
            System.out.println("/n/n/n/n/n"+e.toString()+"/n/n/n/n/n/n/n");
        }
    }*/


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}