package com.example.ezydonate;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BookingMakeActivity extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String date;
//    private Calendar calendar;
    private TimePicker timepicker;
    private Button btn;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.make_booking);
        btn = (Button) findViewById(R.id.loginbtn2);

        timepicker = (TimePicker) findViewById(R.id.timePicker1);
        timepicker.setIs24HourView(true);

        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                date = dayOfMonth + "/" + month + "/" + year;

            }
        });
    }


    public void backBooking(View view) {
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createBooking(View view) {

       // final EditText date = (EditText) findViewById(R.id.editText2);
        final EditText location = (EditText) findViewById(R.id.editText3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int getHour = timepicker.getHour();
                int getMinute = timepicker.getMinute();


                if (getHour > 12){
                    int twelve = getHour - 12;
                }

                if (getMinute < 10){

                }

                time = getHour + " - " + getMinute;

            }
        });

//        if (time.trim().equals("") || date.trim().equals("") || location.getText().toString().trim().equals("")) {
//            Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();

            final String timeString = timepicker.getHour() + "-" + timepicker.getMinute();
            Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            final String id1 = mAuth.getCurrentUser().getUid();
            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("user").child(id1).child("fullName").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {

                    String name = (String) snapshot.getValue();  //prints "Do you have data? You'll love Firebase."

                    Booking booking = new Booking(name, timeString, date, "hi");

                    mDatabase.child("booking").child(id1 + timeString).setValue(booking);

                    Toast.makeText(BookingMakeActivity.this, "Booking Made", Toast.LENGTH_SHORT).show();

                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    String admin = "false";
                }
            });

        }

    }

