package com.example.ezydonate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import butterknife.ButterKnife;

public class BookingCancelActivityAdmin extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private RecyclerView myrecyclerview;
    private List<Booking> lstEvent;
    private FirebaseRecyclerAdapter<Booking, FirebaseBookingViewHolder> mFirebaseAdapter;
    private String userID;

    Query query;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.cancel_booking);

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


            ButterKnife.bind(this);

            mDatabase = FirebaseDatabase.getInstance().getReference().child("bookingadmin");
            query = mDatabase.limitToFirst(50);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewbooking_id);

            setUpFirebaseAdapter(query);
        }

    public void backBooking(View view) {
        finish();
    }

        private void setUpFirebaseAdapter(Query query) {

            FirebaseRecyclerOptions<Booking> options =
                    new FirebaseRecyclerOptions.Builder<Booking>()
                            .setQuery(query, Booking.class)
                            .build();

            mFirebaseAdapter = new FirebaseRecyclerAdapter<Booking, FirebaseBookingViewHolder> (options)
            {

                @NonNull
                @Override
                public FirebaseBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cardview_booking, parent, false);

                    return new FirebaseBookingViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull FirebaseBookingViewHolder holder, int position, @NonNull Booking model) {

                    holder.bindBooking(model);
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

        public void removeBooking(View view, String booking, String id) {

            Toast.makeText(this, "booking removed", Toast.LENGTH_SHORT);
            mDatabase.child(booking+id).removeValue();
            DatabaseReference admin = FirebaseDatabase.getInstance().getReference().child("booking").child(id);
            admin.removeValue();

        }

}
