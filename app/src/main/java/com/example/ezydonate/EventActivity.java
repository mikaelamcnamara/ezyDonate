package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventActivity extends Activity {

    private static final Base64 Base64 = null;
    DrawerLayout dmLayout;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
//    List<Event> lstEvent;

    public Bitmap image;

    public static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.fragment_event);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
     /*   lstEvent = new ArrayList<>();
        lstEvent.add(new Event("The Vegetarian", "yo", "hello","fdksdfk","ninehirty",R.drawable.app_icon));
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstEvent);
        myrv.setLayoutManager(new GridLayoutManager(this, 1));
        myrv.setAdapter(myAdapter);
*/
    }

    public void getImage(View view) {

        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
/*
    public void createEvents(View view) {

        EditText title = (EditText) findViewById(R.id.editText1);
        EditText description = (EditText) findViewById(R.id.editText2);
        EditText location = (EditText) findViewById(R.id.editText3);
        EditText date = (EditText) findViewById(R.id.editText4);
        EditText time = (EditText) findViewById(R.id.editText5);

        Event event = new Event(title.getText().toString(), description.getText().toString(), location.getText().toString(), date.getText().toString(), time.getText().toString(), BitMapToString(image));

        mDatabase.child("events").child(title.getText().toString()).setValue(event);

        mDatabase.child("events").child(title.getText().toString()).child(title.getText().toString()).setValue("hi there");
    }
    */

}
/*

class Event {

    public String title;
    public String description;
    public String location;
    public String eventDate;
    public String time;
    public int image;

    public Event (String title, String description, String location, String eventDate, String time, int image) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.time = time;
        this.image = image;

    }

    public String getDescription() {
        return description;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return image;
    }
    }
*/