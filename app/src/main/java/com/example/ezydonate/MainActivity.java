package com.example.ezydonate;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button b1;
    ImageButton logButton;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    ListView search_bar; // Defining ListView
    ArrayAdapter<String> adapter; // Defining ArrayAdapter;
    static final int requestcode = 1; //RequestCode
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        checkPermission();
        b1 = (Button) findViewById(R.id.loginbtn);
        // mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    public void logout(View view) {

        setContentView(R.layout.activity_main);
    }


        /*Attempt in creating an arraylist for the search
        search_bar = (ListView) findViewById(R.id.search_bar);
        ArrayList<String> arraySearch = new ArrayList<>();



        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.activity_list_item,
                arraySearch
        );
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);


           androidImageButton = (ImageButton) findViewById(R.id.LogOutButton);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }

        });
*/


    public void login(View view) {

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
        setContentView(R.layout.page_main);
    }


    /* public void menu (View view) {
        mDrawerLayout=(DrawerLayout) findViewById(R.id.draw_layout);
        mDrawerLayout.openDrawer(Gravity.LEFT);
        /* mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();*/
    /*}*/



/* This is part of the search function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem item = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
              adapter.getFilter().filter(s);;
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

*/

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

/*

   public boolean onNavigationItemSelected(MenuItem item) {
       int id = item.getItemId();

       if (id == R.id.nav_camera) {

       } else if (id == R.id.nav_gallery) {

       }
       return true;
   }
*/


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
                .setMessage("This permission is necessary to access videos")
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

}

