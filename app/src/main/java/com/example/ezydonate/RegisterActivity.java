package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
        if (email.trim().equals("") || password.trim().equals("")) {
            Toast.makeText(null, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(RegisterActivity.this, "Creating...", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Successful Creation, Please Verify your Email", Toast.LENGTH_SHORT).show();
                    String id1 = mAuth.getCurrentUser().getUid();
                    final FirebaseUser user = mAuth.getCurrentUser();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    //Change later
                    DatabaseReference ref = database.getReference("user/"+id1+"/fullName");
                    ref.setValue(fullName);
                    //Change later
                    ref = database.getReference("user/"+id1+"/username");
                    ref.setValue(username);
                    //Change later
                    ref = database.getReference("user/"+id1+"/email");
                    ref.setValue(email);
                    user.sendEmailVerification();

                    Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(main);

                } else {
                    Toast.makeText(RegisterActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void register(View view) {

        EditText fullName = (EditText) findViewById(R.id.editText2);
        EditText username = (EditText) findViewById(R.id.editText4);
        EditText email = (EditText) findViewById(R.id.editText5);
        EditText password = (EditText) findViewById(R.id.editText9);
        EditText confirmpassword = (EditText) findViewById(R.id.editText7);

        CreateNewUser(mAuth, email.getText().toString(), password.getText().toString(), fullName.getText().toString(), username.getText().toString());



    }

}
