package com.example.ezydonate.Model;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.example.ezydonate.R;
import com.google.firebase.auth.FirebaseAuth;

public class HistoryActivity extends Activity {


    DrawerLayout dmLayout;

    private FirebaseAuth mAuth;
    static final int requestcode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.history_pages);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
    }



}
