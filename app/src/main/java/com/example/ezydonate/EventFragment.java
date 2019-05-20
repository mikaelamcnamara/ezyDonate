package com.example.ezydonate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
        View v;
        private RecyclerView myrecyclerview;
        private List<Event> lstEvent;
        private FirebaseAuth mAuth = FirebaseAuth.getInstance();
        private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_event, container, false);
            myrecyclerview = (RecyclerView) v.findViewById(R.id.recyclerview_id);
            RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstEvent);
            myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            myrecyclerview.setAdapter(recyclerAdapter);
            return v;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            /*List for adding cards */
            lstEvent = new ArrayList<>();
            lstEvent.add(new Event("The Vegetarian", "yo", "hello","fdksdfk","ninehirty", "hi"));
            lstEvent.add(new Event("The Vegetarianss", "yo", "hello","fdksdfk","ninehirty", "hi"));
            lstEvent.add(new Event("The Vegetarian", "yo", "hello","fdksdfk","ninehirty", "hi"));
            lstEvent.add(new Event("The Vegetarianss", "yo", "hello","fdksdfk","ninehirty", "hi"));
        }



        public void makeEvent(View view) {

        Intent makeEvent = new Intent(getActivity(), EventActivity.class);
        startActivity(makeEvent);

    }

}

