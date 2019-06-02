package com.example.ezydonate;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FirebaseEventHistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public Button btnButton1;
    public Button cancelbtn;
    View eView;
    Context eContext;
    private FirebaseAuth mAuth;
    private String userID;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private TextView event_description;
    private LinearLayout events = itemView.findViewById(R.id.transition_container);
    private Button btn;
    private DatabaseReference myRef;


    public FirebaseEventHistViewHolder(View itemView) {
        super(itemView);
        eView = itemView;
        eContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindEvent(Event event) {


        ImageView eventImageView = (ImageView) eView.findViewById(R.id.event_img_id);
        final TextView titleTextView = (TextView) eView.findViewById(R.id.trans_user_id);
        event_description = (TextView) eView.findViewById(R.id.event_description_id);
        TextView dateTextView = (TextView) eView.findViewById(R.id.event_date_id);
        TextView timeTextView = (TextView) eView.findViewById(R.id.event_time_id);
        TextView locationTextView = (TextView) eView.findViewById(R.id.event_location_id);
        btn = itemView.findViewById(R.id.event_more);
        btnButton1 = itemView.findViewById(R.id.event_button_id);

        mAuth = FirebaseAuth.getInstance();
        final String id = mAuth.getCurrentUser().getUid();

        final DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        String id1 = mAuth.getCurrentUser().getUid();

//        root.child("User").child(id1).child("isAdmin").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                String admin = (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."
//
//                if (admin.equals("yes")) {
//
//
//                    cancelbtn = eView.findViewById(R.id.event_button_remove);
//                    btnButton1 = itemView.findViewById(R.id.eventedit_button_id);
//
//                    cancelbtn.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                        @Override
//                        public void onClick(View view) {
//                            final ArrayList<Event> events = new ArrayList<>();
//
//                            ((EventAdmin) eContext).removeEvent(view, titleTextView.getText().toString());
//
//                        }
//                    });
//
//                    btnButton1.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                        @Override
//                        public void onClick(View view) {
//                            final ArrayList<Event> events = new ArrayList<>();
//
//                            ((EventAdmin) eContext).editEvent(view, titleTextView.getText().toString());
//
//                        }
//                    });
//                }
//
//                else {
//
//                    DatabaseReference eventref = root.child("User").child(id).child("attended_events");
//                    eventref.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot snapshot) {
//                            if (snapshot.hasChild(titleTextView.getText().toString())) {
//                                btnButton1.setText("Attending");
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });

//                    btnButton1.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                        @Override
//                        public void onClick(View view) {
//                            final ArrayList<Event> events = new ArrayList<>();
//
//                            ((MainActivity) eContext).attendEvent(view, titleTextView.getText().toString());
//                            btnButton1.setText("Attending");
//
//                        }
//                    });
//                }
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//                String admin = "false";
//            }
//        });


        btn.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(events);
                visible =! visible;
                event_description.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });

        if (!event.getThumbnail().isEmpty()) {

            Picasso.get()
                    .load(event.getThumbnail())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(eventImageView);

        }

        titleTextView.setText(event.getTitle());
        event_description.setText(event.getDescription());
        dateTextView.setText("Date: " + event.getEventDate());
        timeTextView.setText("Time: " + event.getTime());
        locationTextView.setText(event.getLocation());

    }

    @Override
    public void onClick(View view) {
        final ArrayList<Event> events = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    events.add(snapshot.getValue(Event.class));
                }

//                MainActivity.get
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }




}
