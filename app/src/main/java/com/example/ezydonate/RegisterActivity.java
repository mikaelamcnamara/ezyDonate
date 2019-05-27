package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends Activity {

    DrawerLayout dmLayout;

    private FirebaseAuth mAuth;
    static final int requestcode = 1;

    //lmaoooooo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.register_page);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);

    }

    public void CreateNewUser(final FirebaseAuth mAuth,  final String email, final String password, final String fullName, final String username) {
       final User newUser = new User(email, fullName, username,"None");
        if (email.trim().equals("") || password.trim().equals("") || fullName.trim().equals("") || username.trim().equals("")) {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
        else {
            //Changed to object system can use it for images now
            Toast.makeText(this, "Creating...", Toast.LENGTH_SHORT).show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Successful Creation, Please Verify your Email", Toast.LENGTH_SHORT).show();
                        String id1 = mAuth.getCurrentUser().getUid();
                        final FirebaseUser user = mAuth.getCurrentUser();
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("User").child(id1).setValue(newUser);
                        user.sendEmailVerification();
                        finish();

                    } else
                        {
                        Toast.makeText(RegisterActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void cancel(View view) {
        finish();
    }

    public void register(View view) {

        EditText fullName = (EditText) findViewById(R.id.editText1);
        EditText username = (EditText) findViewById(R.id.editText3);
        EditText email = (EditText) findViewById(R.id.editText5);
        EditText password = (EditText) findViewById(R.id.editText9);
        EditText confirmpassword = (EditText) findViewById(R.id.editText7);


        CreateNewUser(mAuth, email.getText().toString(), password.getText().toString(), fullName.getText().toString(), username.getText().toString());

        }
    }

