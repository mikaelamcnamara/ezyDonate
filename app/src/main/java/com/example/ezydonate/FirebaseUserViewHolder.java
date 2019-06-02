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

public class FirebaseUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

    public FirebaseUserViewHolder(View itemView) {
        super(itemView);
        eView = itemView;
        eContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindUser(User user) {

//        ImageView eventImageView = (ImageView) eView.findViewById(R.id.event_img_id);
//        final TextView titleTextView = (TextView) eView.findViewById(R.id.event_title_id);
        TextView username = (TextView) eView.findViewById(R.id.user_title_id);
        TextView name = (TextView) eView.findViewById(R.id.user_name_id);
        TextView email = (TextView) eView.findViewById(R.id.user_email_id);
        TextView donation = (TextView) eView.findViewById(R.id.user_donation_id4);
       // TextView locationTextView = (TextView) eView.findViewById(R.id.event_location_id);

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference eventref = root.child("User").child(id).child("attended_events");
//        eventref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.hasChild(titleTextView.getText().toString())) {
//                    btnButton1.setText("Attending");
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

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


//        btn.setOnClickListener(new View.OnClickListener() {
//            boolean visible;
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View v) {
//                TransitionManager.beginDelayedTransition(events);
//                visible =! visible;
//                event_description.setVisibility(visible ? View.VISIBLE : View.GONE);
//            }
//        });

//        Picasso.get()
//                .load(event.getThumbnail())
//                .resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(eventImageView);

        username.setText(user.getUsername());
        name.setText(user.getName());
        email.setText("Email: " + user.getEmail());
        donation.setText("Amount Donated: $" + user.getDonation());
//        timeTextView.setText("Time: " + user.getTime());
//        locationTextView.setText(user.getLocation());

    }

    @Override
    public void onClick(View view) {
//        final ArrayList<User> users = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    users.add(snapshot.getValue(User.class));
//                }
//
////                MainActivity.get
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
    }


}
