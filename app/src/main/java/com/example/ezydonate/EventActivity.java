package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EventActivity extends Activity {

    DrawerLayout dmLayout;
    List<Event> lstData;
    List<Event> lstFullData;
    SearchView searchView;
    ListView listview;
    private List<Event> lstEvent;
    private RecyclerViewAdapter adapter;
    ViewGroup tContainer;
    TextView txt;
    private List<Event> exampleList;
    private List<Event> exampleListFull;
    Button btn;


    private LinearLayout event;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private Uri image_uri;
    private String download_uri;
//    List<Event> lstEvent;

    public Bitmap image;

    public static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.make_event);
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
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            image_uri = data.getData();
            image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image_uri);
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

    public void menu(View view) {
        dmLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        dmLayout.openDrawer(Gravity.LEFT);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
    }


    public void createEvents(View view) {

        final EditText title = (EditText) findViewById(R.id.editText1);
        final EditText description = (EditText) findViewById(R.id.editText2);
        final EditText location = (EditText) findViewById(R.id.editText3);
        final EditText date = (EditText) findViewById(R.id.editText4);
        final EditText time = (EditText) findViewById(R.id.editText5);

        if (title.getText().toString().trim().equals("") || description.getText().toString().trim().equals("") || location.getText().toString().trim().equals("") || date.getText().toString().trim().equals("") || time.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Invalid Details", Toast.LENGTH_SHORT).show();
        }

        else {
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

                            Event event = new Event(title.getText().toString(), description.getText().toString(), location.getText().toString(), date.getText().toString(), time.getText().toString(), download_uri);

                            mDatabase.child("events").child(title.getText().toString()).setValue(event);

                            Toast.makeText(EventActivity.this, "event creation successful", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                    });


                }
            });
        }
    }


//    //public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem searchItem = menu.findItem((R.id.search_view));
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//        return true;
//    }
//    //@Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        adapter.getFilter().filter(newText);
//        return false;
//    }
//}
}