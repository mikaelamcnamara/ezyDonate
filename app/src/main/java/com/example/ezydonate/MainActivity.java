package com.example.ezydonate;
import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout dmLayout;


    Button b1;
    private FirebaseAuth mAuth;
    static final int requestcode = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
        b1 = (Button) findViewById(R.id.loginbtn);
        checkPermission();



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainMenuFragment()).commit();
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragment()).commit();
                break;
            case R.id.nav_event:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EventFragment()).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_booking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BookingFragment()).commit();
                break;
        }
        dmLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void forgotPass_page(View view) {

        Intent forgot_password  = new Intent(MainActivity.this, ForgotActivity.class);
        startActivity(forgot_password);
    }

    public void register_page(View view) {

        Intent register  = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(register);
    }

    public void makeEvent(View view) {

        Intent makeEvent = new Intent(MainActivity.this, EventActivity.class);
        startActivity(makeEvent);

    }


    public void menu(View view) {
        dmLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        dmLayout.openDrawer(Gravity.LEFT);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        //Toast.makeText(null,"Login Working", Toast.LENGTH_SHORT).show();
        EditText username = (EditText) findViewById(R.id.editText2);
        EditText password = (EditText) findViewById(R.id.editText);

        signIn(mAuth, username.getText().toString(), password.getText().toString());

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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
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


    public void signIn(final FirebaseAuth mAuth, String email, String password) {

        if (email.trim().equals("") || password.trim().equals("")) {


        }

        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            final FirebaseUser userTest = mAuth.getCurrentUser();
                            if (task.isSuccessful() && userTest.isEmailVerified()) {
                                Toast.makeText(MainActivity.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                setContentView(R.layout.page_main);
                            }

                            else if (task.isSuccessful() && !userTest.isEmailVerified()) {
                                Toast.makeText(MainActivity.this, "Email is not verified - Please Verify your Email",
                                        Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
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

}