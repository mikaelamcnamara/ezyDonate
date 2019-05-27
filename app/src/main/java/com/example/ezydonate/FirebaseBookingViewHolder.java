package com.example.ezydonate;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseBookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View eView;
    Context eContext;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private TextView event_description;
    private LinearLayout events = itemView.findViewById(R.id.transition_container);
    private Button btn;

    public FirebaseBookingViewHolder(View itemView) {
        super(itemView);
        eView = itemView;
        eContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBooking(Booking booking) {



        TextView dateTextView = (TextView) eView.findViewById(R.id.booking_date_id);
        TextView timeTextView = (TextView) eView.findViewById(R.id.booking_time_id);
        TextView locationTextView = (TextView) eView.findViewById(R.id.booking_location_id);


        dateTextView.setText("Date:" + booking.getDate());
        timeTextView.setText("Time" + booking.getTime());
        locationTextView.setText(booking.getLocation());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Booking> bookings = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("booking");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    bookings.add(snapshot.getValue(Booking.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(eContext, BookingCancelActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("events", Parcels.wrap(bookings));

                eContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
