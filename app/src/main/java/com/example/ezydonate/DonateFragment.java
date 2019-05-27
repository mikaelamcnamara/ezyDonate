package com.example.ezydonate;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ezydonate.Model.Donation;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import butterknife.ButterKnife;

//lmao

public class DonateFragment extends Fragment {
        View v;
        private RecyclerView myrecyclerview;
        private List<Event> lstEvent;
        private FirebaseAuth mAuth = FirebaseAuth.getInstance();
        private FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> mFirebaseAdapter;
        private DatabaseReference mDatabase;
        Query query;


        RecyclerView mRecyclerView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.donate, container, false);

            return v;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {

            /*List for adding cards */
            super.onCreate(savedInstanceState);


        }

    }


