package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;

import java.util.ArrayList;
import java.util.List;
//lmao
import butterknife.BindViews;
import butterknife.ButterKnife;

public class EventFragment extends Fragment {
        View v;
        private RecyclerView myrecyclerview;
        private List<Event> lstEvent;
        private FirebaseAuth mAuth = FirebaseAuth.getInstance();
        private FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> mFirebaseAdapter;
        public SearchView search;
        private DatabaseReference mDatabase;
        Query query;

        RecyclerView mRecyclerView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_user_event, container, false);

            ButterKnife.bind(getActivity());
            setHasOptionsMenu(true);

            mDatabase = FirebaseDatabase.getInstance().getReference().child("events");
            query = mDatabase.limitToFirst(50);

            mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);

            setUpFirebaseAdapter(query);

            return v;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.menu_main, menu);
            super.onCreateOptionsMenu(menu,inflater);
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {

            /*List for adding cards */
            super.onCreate(savedInstanceState);


        }

        private void setUpFirebaseAdapter(Query query) {

            FirebaseRecyclerOptions<Event> options =
                    new FirebaseRecyclerOptions.Builder<Event>()
                            .setQuery(query, Event.class)
                            .build();

            mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> (options)
                    {

                @NonNull
                @Override
                public FirebaseEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.carduser_event, parent, false);

                    return new FirebaseEventViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull FirebaseEventViewHolder holder, int position, @NonNull Event model) {

                    holder.bindEvent(model);


                }

            };
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mFirebaseAdapter.startListening();
            mRecyclerView.setAdapter(mFirebaseAdapter);

            if (search != null) {

                search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //newSearch();
                        return false;
                    }
                });
            }

//
        }

        private void newSearch(String s) {

            ArrayList<Event> myList = new ArrayList<>();
            for ( Event object : myList) {


            }
        }

        public void makeEvent(View view) {

            Intent makeEvent = new Intent(getActivity(), EventActivity.class);
            startActivity(makeEvent);
        }

}

