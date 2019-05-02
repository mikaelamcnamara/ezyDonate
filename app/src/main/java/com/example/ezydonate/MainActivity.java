package com.example.ezydonate;
import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dmLayout;

    Button b1;
    Button b2;
    private FirebaseAuth mAuth;
    static final int requestcode = 1;

    //lmaoooooo
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


    public void forgotPass (View view) {
        setContentView(R.layout.forgot_user_password);
    }

    public void go_register (View view) {

        setContentView(R.layout.register_page);

    }

    public void register (View view) {

        EditText fullName = (EditText) findViewById(R.id.editText2);
        EditText username = (EditText) findViewById(R.id.editText4);
        EditText email = (EditText) findViewById(R.id.editText5);
        EditText password = (EditText) findViewById(R.id.editText9);
        EditText confirmpassword = (EditText) findViewById(R.id.editText7);

            CreateNewUser(mAuth, email.getText().toString(), password.getText().toString(), fullName.getText().toString(), username.getText().toString());

    }

    public void menu (View view) {
        dmLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        dmLayout.openDrawer(Gravity.LEFT);
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //PERMISSION GRANTED
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
        } else {
            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }
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

    public void logout(View view) { setContentView(R.layout.activity_main); }

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

    public void forget_password(View view) {

        EditText email = (EditText) findViewById(R.id.editText8);

        reset_password(mAuth, email.getText().toString());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == MainActivity.requestcode) {
            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            showDetails();

        }
    }

    public void showDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Storage  Write Permission")
                .setMessage("This permission is necessary to access storage to be able to save data")
                .setPositiveButton(android.R.string.ok, new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.create();
        builder.show();

    }



    public void CreateNewUser(final FirebaseAuth mAuth,  final String email, final String password, final String fullName, final String username) {
        if (email.trim().equals("") || password.trim().equals("")) {
            Toast.makeText(null, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(MainActivity.this, "Creating...", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Successful Creation", Toast.LENGTH_SHORT).show();
                    String id1 = mAuth.getCurrentUser().getUid();
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
                    setContentView(R.layout.activity_main);
                } else {
                    Toast.makeText(MainActivity.this, "An error has occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void reset_password(final FirebaseAuth mAuth, final String email) {

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Password Reset Request Sent, Check Your Email", Toast.LENGTH_SHORT).show();
                            setContentView(R.layout.activity_main);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Email Address Does Not Exist",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    public void signIn(final FirebaseAuth mAuth, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    //@Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            setContentView(R.layout.page_main);
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

