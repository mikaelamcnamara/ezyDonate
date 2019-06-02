package com.example.ezydonate;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ezydonate.Model.Donation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout dmLayout;


    Button b1;
    private FirebaseAuth mAuth;
    static final int requestcode = 1;
    private DatabaseReference mDatabase;
    private User uInfo;
    private TextView tv1;
    private DatabaseReference myRef;
    private DatabaseReference Donors;
    private User[] topDonators = new User[3];
    private User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.loginbtn);
        checkPermission();

        Donors = (DatabaseReference) FirebaseDatabase.getInstance().getReference("User");
        Donors.orderByChild("donation").limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = ((int) dataSnapshot.getChildrenCount());
                for(DataSnapshot user: dataSnapshot.getChildren())
                {
                    i--;
                    if(i >=0)
                    {
                        topDonators[i] = user.getValue(User.class);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
            case R.id.nav_booking:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BookingFragment()).commit();
                break;
            case R.id.nav_donate:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DonateFragment()).commit();
                break;
            case R.id.nav_location:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LocationFragment()).commit();
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

    public void event(View view) {
        setContentView(R.layout.cardview_event);
    }

    public void adminEvent(View view) {

        Intent eventAdmin  = new Intent(MainActivity.this, EventAdmin.class);
        startActivity(eventAdmin);
    }



    public void booking(View view) {
        setContentView(R.layout.admin_bookingview);
    }

    public void history(View view) {
        setContentView(R.layout.fragment_history);
    }

    public void account(View view) {
        setContentView(R.layout.fragment_account);
    }

    public void accountA(View view) {
        setContentView(R.layout.fragment_users);
    }

    public void mainmenuA(View view) {
        setContentView(R.layout.mainadmin_page);
    }


    public void donate(View view) {
        setContentView(R.layout.donate);
    }

    public void mainmenu(View view) {
        setContentView(R.layout.page_main);

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

    public void makeBooking(View view) {

        Intent makeEvent = new Intent(MainActivity.this, BookingMakeActivity.class);
        startActivity(makeEvent);

    }

    public void cancelBooking(View view) {

        Intent makeEvent = new Intent(MainActivity.this, BookingCancelActivity.class);
        startActivity(makeEvent);

    }

    public void showusers(View view) {

        Intent showUsers = new Intent(MainActivity.this, UserAdminFragment.class);
        startActivity(showUsers);

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
        FirebaseUser user = mAuth.getCurrentUser();

        mDatabase.child("User").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                currentUser = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
            Toast.makeText(MainActivity.this, "Connecting...", Toast.LENGTH_SHORT).show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            final FirebaseUser userTest = mAuth.getCurrentUser();
                            if (task.isSuccessful() && userTest.isEmailVerified()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                String id1 = mAuth.getCurrentUser().getUid();
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

                                mDatabase.child("User").child(id1).child("isAdmin").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {

                                       String admin = (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."

                                        if (admin.equals("yes")) {


                                            setContentView(R.layout.mainadmin_page);
                                        }

                                        else {

                                            setContentView(R.layout.page_main);
                                            TextView donationAmount = (TextView) findViewById(R.id.textView14);
                                            donationAmount.setText("$" + String.format("%.2f",currentUser.getDonation()));

                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                        String admin = "false";
                                    }
                                });

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void makedonate(View view) {

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        final String id1 = user.getUid();

        myRef = mFirebaseDatabase.getReference("User/"  + id1 + "/");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        final EditText title = (EditText) findViewById(R.id.editText9);

        if (title.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
        }

        else {

            final double amount = (Double.parseDouble(title.getText().toString()));

            Donation donation = new Donation(amount, id1, dtf.format(now));

            mDatabase.child("donation").child(id1 + " " + dtf.format(now)).setValue(donation);

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Get Post object and use the values to update the UI
                    //Donation donation = dataSnapshot.getValue(Donation.class);

                    uInfo = new User();
                    String donate = dataSnapshot.child("donation").getValue().toString();

                    uInfo.setDonation(Double.parseDouble((donate)));

                    double total = amount + uInfo.getDonation();

                    myRef.child("donation").setValue(total);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.w( "loadPost:onCancelled", databaseError.toException());
                    // ...
                }
            });

            Toast.makeText(this, "Donation successful", Toast.LENGTH_SHORT).show();
        }

    }

    public void attendEvent(View view, String event) {

//        tv1 = findViewById(R.id.event_title_id);
//
//        final String event = tv1.getText().toString();

        FirebaseUser user = mAuth.getCurrentUser();
        final String id1 = user.getUid();

        mDatabase.child("User").child(id1).child("attended_events").child(event).setValue(true);

    }

}