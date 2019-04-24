package com.example.ezydonate;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button b1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);

        b1 = (Button) findViewById(R.id.loginbtn);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void login(View view) {
        // R means res, layout is the package under res //

        EditText username = (EditText) findViewById(R.id.editText3);
        EditText password = (EditText) findViewById(R.id.editText);

        int i = 0;
        i++;

        /*
        if (username.getText().toString().equals("jshkeeg@gmail.com") && password.getText().toString().equals("adminss")) {

            CreateNewUser(mAuth, username.getText().toString(), password.getText().toString());
            setContentView(R.layout.content_main);

        } else if (i == 3) {

            b1.setEnabled(false);
        }
        */
        signIn(mAuth, username.getText().toString(),password.getText().toString());


    }

    public void register(View view) {
        // R means res, layout is the package under res //


        setContentView(R.layout.register_main);
    }

    public void forgotPass(View view) {
        // R means res, layout is the package under res //


        setContentView(R.layout.content_main);
    }

    public void logout(View view) {

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
    /*    if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }*/
        return false;

    }

    public Boolean CreateNewUser(final FirebaseAuth mAuth, String email, String password) {


        if (email.trim().equals("") || password.trim().equals("")) {
            Toast.makeText(null, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(MainActivity.this, "Creating...", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Successful Creation", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return true;
    }

    public void signIn(final FirebaseAuth mAuth, String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //@Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            setContentView(R.layout.content_main);

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}

