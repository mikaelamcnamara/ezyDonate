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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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
    private Button cancelbtn;
    private FirebaseAuth mAuth;


    public FirebaseBookingViewHolder(View itemView) {
        super(itemView);
        eView = itemView;
        eContext = itemView.getContext();
        itemView.setOnClickListener(this);


    }

    public void bindBooking(Booking booking) {



        TextView dateTextView = (TextView) eView.findViewById(R.id.booking_date_id);
        final TextView timeTextView = (TextView) eView.findViewById(R.id.booking_time_id);
        TextView locationTextView = (TextView) eView.findViewById(R.id.booking_location_id);

        final String Time = booking.getTime();

        dateTextView.setText("Date: " + booking.getDate());
        timeTextView.setText("Time: " + booking.getTime());
        locationTextView.setText(booking.getDescription());

        mAuth = FirebaseAuth.getInstance();
        final String id = mAuth.getCurrentUser().getUid();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        cancelbtn = eView.findViewById(R.id.event_button_remove);


                    cancelbtn = eView.findViewById(R.id.booking_button_id3);

                    cancelbtn.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onClick(final View view) {
                            final ArrayList<Event> events = new ArrayList<>();

                            final String booking_name = Time;
                            String booking_desc = id;

                            DatabaseReference g = FirebaseDatabase.getInstance().getReference();

                           g.child("User").child(id).child("isAdmin").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {

                                    String admin = (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."

                                    if (admin.equals("yes")) {


                                        ((BookingCancelActivityAdmin) eContext).removeBooking(view, booking_name, id);
                                    }

                                    else {

                                        ((BookingCancelActivity) eContext).removeBooking(view, booking_name, id);
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    String admin = "false";
                                }
                            });


                        }
                    });

                }

    @Override
    public void onClick(View view) {
//        final ArrayList<Booking> bookings = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("booking");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    bookings.add(snapshot.getValue(Booking.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(eContext, BookingCancelActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("events", Parcels.wrap(bookings));
//
//                eContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }


}
