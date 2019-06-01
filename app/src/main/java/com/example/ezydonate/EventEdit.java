package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventEdit extends Activity {

    private FirebaseAuth mAuth;
    static final int requestcode = 1;
    private DatabaseReference mDatabase;
    private DatabaseReference eventDatabase;
    private DatabaseReference myRef;
    String TAG = "error";
    String eventTitle;
    private Event eventinfo;
    private EditText etTitle;
    private EditText etDesc;
    private EditText etLocation;
    private EditText etDate;
    private EditText etTime;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private Uri image_uri;
    private String download_uri;
    public Bitmap image;
    public static final int GET_FROM_GALLERY = 3;
    private String original_download;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_event);

        Bundle extras = getIntent().getExtras();
        eventTitle = extras.getString("eventName");
        Toast.makeText(this, eventTitle, Toast.LENGTH_SHORT).show();

        ButterKnife.bind(this);

        etTitle = (EditText) findViewById(R.id.editText1);
        etDesc = (EditText) findViewById(R.id.editText2);
        etLocation = (EditText) findViewById(R.id.editText3);
        etDate = (EditText) findViewById(R.id.editText4);
        etTime = (EditText) findViewById(R.id.editText5);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        myRef = mDatabase.child("events").child(eventTitle);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w(TAG, "Failed to read value.");
            }
        });

    }



    private void showData(DataSnapshot dataSnapshot) {

        eventinfo = new Event();

        //toastMessage(dataSnapshot.child("title").getValue().toString());
        eventinfo.setTitle(dataSnapshot.child("title").getValue().toString());
        eventinfo.setDescription(dataSnapshot.child("description").getValue().toString());
        eventinfo.setLocation(dataSnapshot.child("location").getValue().toString());
        eventinfo.setEventDate(dataSnapshot.child("eventDate").getValue().toString());
        eventinfo.setTime(dataSnapshot.child("time").getValue().toString());
        eventinfo.setImage(dataSnapshot.child("image").getValue().toString());

        etTitle.setText(eventinfo.getTitle());
        etDesc.setText(eventinfo.getDescription());
        etLocation.setText(eventinfo.getLocation());
        etDate.setText(eventinfo.getEventDate());
        etTime.setText(eventinfo.getTime());
        original_download = eventinfo.getThumbnail();

    }

    @OnClick(R.id.editText1)public void updateTitle(){
        if(etTitle.getText().toString().trim().equals("")){
            toastMessage("Please enter new email.");
        }else {

            eventDatabase = FirebaseDatabase.getInstance().getReference();

            eventDatabase.child("events").child(etTitle.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("title").setValue(etTitle.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("description").setValue(etDesc.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("title").setValue(etTitle.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("location").setValue(etLocation.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("date").setValue(etDate.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("time").setValue(etTime.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("image").setValue(etTime.getText().toString());
            eventDatabase.child("events").child(etTitle.getText().toString()).child("thumbnail").setValue(original_download);

            myRef.removeValue();

            toastMessage("Title successfully updated.");
        }
    }

    @OnClick(R.id.editText2)public void updateDescription(){
        if(etDesc.getText().toString().trim().equals("")){
            toastMessage("Please enter new name.");
        }else {
            myRef.child("description").setValue(etDesc.getText().toString());
            toastMessage("Description successfully updated.");
        }
    }

    @OnClick(R.id.editText3)public void updateLocation(){
        if(etLocation.getText().toString().trim().equals("")){
            toastMessage("Please enter new name.");
        }else {
            myRef.child("location").setValue(etLocation.getText().toString());
            toastMessage("Location successfully updated.");
        }
    }

    @OnClick(R.id.editText4)public void updateDate(){
        if(etDate.getText().toString().trim().equals("")){
            toastMessage("Please enter new name.");
        }else {
            myRef.child("date").setValue(etDate.getText().toString());
            toastMessage("Date successfully updated.");
        }
    }

    @OnClick(R.id.editText5)public void updateTime(){
        if(etTime.getText().toString().trim().equals("")){
            toastMessage("Please enter new name.");
        }else {
            myRef.child("time").setValue(etTime.getText().toString());
            toastMessage("Time successfully updated.");
        }
    }

    public void getImage(View view) {

        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            image_uri = data.getData();
            image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image_uri);

                final StorageReference eventRef = storageRef.child("event_images/" + image_uri.getLastPathSegment());
                UploadTask uploadTask = eventRef.putFile(image_uri);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                download_uri = uri.toString();
                                myRef.child("image").setValue(download_uri);
                                myRef.child("thumbnail").setValue(download_uri);
                                toastMessage("Image successfully updated.");
                            }
                        });

                    }
                });

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void cancel(View view) {

        finish();
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
