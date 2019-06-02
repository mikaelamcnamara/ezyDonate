package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ezydonate.Model.Donation;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import butterknife.ButterKnife;

public class AdminHistory extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private RecyclerView myrecyclerview;
    private List<Booking> lstEvent;
    private FirebaseRecyclerAdapter<Donation, FirebaseTransHistViewHolder> mFirebaseAdapter;

    Query query;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.admin_history);

    }

    public void eventHistory(View view) {

        Intent eventhistory = new Intent(this, EventHistoryPage.class);
        startActivity(eventhistory);

    }

    public void donationHistory(View view) {

        Intent donationhist = new Intent(this, TransHistoryPage.class);
        startActivity(donationhist);

    }

}
