package com.example.ezydonate;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDonationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
    private String key;

    public FirebaseDonationViewHolder(View itemView) {
        super(itemView);
        eView = itemView;
        eContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindUser(User user) {

//        ImageView eventImageView = (ImageView) eView.findViewById(R.id.event_img_id);
//        final TextView titleTextView = (TextView) eView.findViewById(R.id.event_title_id);
        final TextView username = (TextView) eView.findViewById(R.id.user_title_id);
        TextView name = (TextView) eView.findViewById(R.id.user_name_id);
        final TextView email = (TextView) eView.findViewById(R.id.user_email_id);
        TextView donation = (TextView) eView.findViewById(R.id.user_donation_id4);
       // TextView locationTextView = (TextView) eView.findViewById(R.id.event_location_id);

        mAuth = FirebaseAuth.getInstance();
        final String id = mAuth.getCurrentUser().getUid();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference();

        String id1 = mAuth.getCurrentUser().getUid();


        cancelbtn = eView.findViewById(R.id.user_button_id);

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        Query query = rootRef.child("User").orderByChild("email").equalTo(user.getEmail());
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    key = ds.getKey();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                ((UserAdminFragment) eContext).removeUser(view, key);

            }
        });


        username.setText(user.getUsername());
        name.setText(user.getName());
        email.setText("Email: " + user.getEmail());
        donation.setText("Amount Donated: $" + user.getDonation());

    }

    @Override
    public void onClick(View view) {
//
    }


}
