package com.example.ezydonate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import butterknife.ButterKnife;

public class EventHistoryPage extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private RecyclerView myrecyclerview;
    private List<Booking> lstEvent;
    private FirebaseRecyclerAdapter<Event, FirebaseEventHistViewHolder> mFirebaseAdapter;

    Query query;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.fragment_eventhistory);


        ButterKnife.bind(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("events");
        query = mDatabase.limitToFirst(50);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewhistory_id);

        setUpFirebaseAdapter(query);
    }




    public void backBooking(View view) {
        finish();
    }

    private void setUpFirebaseAdapter(Query query) {

        FirebaseRecyclerOptions<Event> options =
                new FirebaseRecyclerOptions.Builder<Event>()
                        .setQuery(query, Event.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventHistViewHolder> (options)
        {

            @NonNull
            @Override
            public FirebaseEventHistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_cardview_eventhist, parent, false);

                return new FirebaseEventHistViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FirebaseEventHistViewHolder holder, int position, @NonNull Event model) {

                holder.bindEvent(model);
            }

//                @Override
//                protected void populateViewHolder(FirebaseEventViewHolder viewHolder,
//                                                  Event model, int position) {
//                    viewHolder.bindEvent(model);
//                }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseAdapter.startListening();
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    public void removeBooking(View view, String booking) {

        Toast.makeText(this, booking, Toast.LENGTH_SHORT).show();

        mDatabase.child(booking).removeValue();

    }

}
