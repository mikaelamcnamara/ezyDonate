package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

//lmao

public class UserAdminFragment extends Activity {
        View v;
        private RecyclerView myrecyclerview;
        private List<Event> lstEvent;
        private FirebaseAuth mAuth = FirebaseAuth.getInstance();
        private FirebaseRecyclerAdapter<User, FirebaseUserViewHolder> mFirebaseAdapter;
        public SearchView search;
        private DatabaseReference mDatabase;
        Query query;

        RecyclerView mRecyclerView;

        @Nullable

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();
            setContentView(R.layout.fragment_users);


            ButterKnife.bind(this);

            mDatabase = FirebaseDatabase.getInstance().getReference().child("User");
            query = mDatabase.limitToFirst(50);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewuser_id);

            setUpFirebaseAdapter(query);
        }

        private void setUpFirebaseAdapter(Query query) {

            FirebaseRecyclerOptions<User> options =
                    new FirebaseRecyclerOptions.Builder<User>()
                            .setQuery(query, User.class)
                            .build();

            mFirebaseAdapter = new FirebaseRecyclerAdapter<User, FirebaseUserViewHolder> (options)
                    {

                @NonNull
                @Override
                public FirebaseUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.card_users, parent, false);

                    return new FirebaseUserViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull FirebaseUserViewHolder holder, int position, @NonNull User model) {

                    holder.bindUser(model);


                }

            };
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mFirebaseAdapter.startListening();
            mRecyclerView.setAdapter(mFirebaseAdapter);
//
        }

    public void backUser(View view) {
        finish();
    }

    public void removeUser(View view, String user) {

       // Toast.makeText(this, user, Toast.LENGTH_SHORT).show();

        mDatabase.child(user).removeValue();

    }

}

