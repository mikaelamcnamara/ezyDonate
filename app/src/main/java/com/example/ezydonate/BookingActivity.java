package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class BookingActivity extends Activity {

    DrawerLayout dmLayout;

    private FirebaseAuth mAuth;
    static final int requestcode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.register_page);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);

    }

    public void makebooking (View view) {

        Intent book  = new Intent(BookingActivity.this, BookingActivity.class);

    }




}
