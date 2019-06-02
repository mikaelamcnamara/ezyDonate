package com.example.ezydonate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.history_pages, container, false);
        }


        public void eventHistory(View view) {

            Intent eventhistory = new Intent(getActivity(), EventHistoryPage.class);
            startActivity(eventhistory);

        }

        public void donationHistory(View view) {

            Intent donateHistory = new Intent(getActivity(), TransHistoryPage.class);
            startActivity(donateHistory);

        }
    }

//        View v;
//        private RecyclerView myrecyclerview;
//        private List<Event> lstEvent;
//        private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        private FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> mFirebaseAdapter;
//        private DatabaseReference mDatabase;
//        Query query;
//
//        RecyclerView mRecyclerView;
//
//        @Nullable
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            v = inflater.inflate(R.layout.fragment_history, container, false);
//
//            ButterKnife.bind(getActivity());
//
//            mDatabase = FirebaseDatabase.getInstance().getReference().child("events");
//            query = mDatabase.limitToFirst(50);
//
//            mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerviewhistory_id);
//
//            setUpFirebaseAdapter(query);
////
////            RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstEvent);
////            myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
////            myrecyclerview.setAdapter(recyclerAdapter);
//            return v;
//        }
//
//        @Override
//        public void onCreate(@Nullable Bundle savedInstanceState) {
//
//            /*List for adding cards */
//            super.onCreate(savedInstanceState);
//
//
//        }
//
//        private void setUpFirebaseAdapter(Query query) {
//
//            FirebaseRecyclerOptions<Event> options =
//                    new FirebaseRecyclerOptions.Builder<Event>()
//                            .setQuery(query, Event.class)
//                            .build();
//
//            mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> (options)
//            {
//
//                @NonNull
//                @Override
//                public FirebaseEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//                    View view = LayoutInflater.from(parent.getContext())
//                            .inflate(R.layout.carduser_event, parent, false);
//
//                    return new FirebaseEventViewHolder(view);
//                }
//
//                @Override
//                protected void onBindViewHolder(@NonNull FirebaseEventViewHolder holder, int position, @NonNull Event model) {
//
//                    holder.bindEvent(model);
//                }
//
////                @Override
////                protected void populateViewHolder(FirebaseEventViewHolder viewHolder,
////                                                  Event model, int position) {
////                    viewHolder.bindEvent(model);
////                }
//            };
//            mRecyclerView.setHasFixedSize(true);
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            mFirebaseAdapter.startListening();
//            mRecyclerView.setAdapter(mFirebaseAdapter);
//        }
//
//}