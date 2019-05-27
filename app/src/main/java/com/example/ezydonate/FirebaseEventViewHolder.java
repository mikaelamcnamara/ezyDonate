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
import com.google.firebase.database.core.Constants;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseEventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View eView;
    Context eContext;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    private TextView event_description;
    private LinearLayout events = itemView.findViewById(R.id.transition_container);
    private Button btn;

    public FirebaseEventViewHolder(View itemView) {
        super(itemView);
        eView = itemView;
        eContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindEvent(Event event) {



        ImageView eventImageView = (ImageView) eView.findViewById(R.id.event_img_id);
        TextView titleTextView = (TextView) eView.findViewById(R.id.event_title_id);
        event_description = (TextView) eView.findViewById(R.id.event_description_id);
        TextView dateTextView = (TextView) eView.findViewById(R.id.event_date_id);
        TextView timeTextView = (TextView) eView.findViewById(R.id.event_time_id);
        TextView locationTextView = (TextView) eView.findViewById(R.id.event_location_id);
        btn = itemView.findViewById(R.id.event_more);
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


        Picasso.get()
                .load(event.getThumbnail())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(eventImageView);

        titleTextView.setText(event.getTitle());
        event_description.setText(event.getDescription());
        dateTextView.setText("Date:" + event.getEventDate());
        timeTextView.setText("Time" + event.getTime());
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

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(eContext, EventFragment.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("events", Parcels.wrap(events));

                eContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
