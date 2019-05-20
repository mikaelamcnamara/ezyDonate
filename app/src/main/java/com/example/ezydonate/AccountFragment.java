package com.example.ezydonate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountFragment extends Fragment {

    private static final String TAG = "MainActivity";

    /**
     * Firebase stuff
     */
    //FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //private DatabaseReference myRef;
    //private String userID;
    //private UserInformation uInfo;


    /**
     * UI references
     */
    @BindView(R.id.editText10) EditText etEmail;
    @BindView(R.id.textView17) TextView tvUpEmail;

    @BindView(R.id.editText14)EditText etFullname;
    @BindView(R.id.textView24) TextView tvFullname;

    @BindView(R.id.editText12) EditText etPassword;
    @BindView(R.id.textView20) TextView tvPassword;


    public AccountFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Note the use of getActivity() to reference the Activity holding this fragment
        getActivity().setTitle("Account Information");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);

        ButterKnife.bind(this, v);

        return v;
    }



    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        //mFirebaseDatabase = FirebaseDatabase.getInstance();
        //myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        //userID = user.getUid();
        updateUI(user);
    //    mAuthListener = new FirebaseAuth.AuthStateListener() {
     //       @Override
    //        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    //            FirebaseUser user = firebaseAuth.getCurrentUser();
    //            if (user != null) {
    //                // User is signed in
    //                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
    //                toastMessage("Successfully signed in with: " + user.getEmail());
    //            } else {
   //                 // User is signed out
    //                Log.d(TAG, "onAuthStateChanged:signed_out");
     //               toastMessage("Successfully signed out.");
 //               }

     //       }
      //  };
        /**
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value + add exception
                Log.w(TAG, "Failed to read value.");
            }
        });
        */
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        String userID = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference("user/" + userID + "/email");

        etEmail.setText(ref1.d);
        //etFullname.setText(user.);
        /**
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
            findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

            findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
            findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
            findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
         */
    }
    /**
     * Get data from firebase and store in local string
     * @param dataSnapshot
     *//**
    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            uInfo = new UserInformation();
            uInfo.setFullname(ds.child(userID).getValue(UserInformation.class).getFullname()); //set the name
            uInfo.setEmail(ds.child(userID).getValue(UserInformation.class).getEmail()); //set the email
            //uInfo.setPassword(ds.child(userID).getValue(UserInformation.class).getPassword()); //set the password


            tvFullname.setText("Full Name: " + uInfo.getFullname());
            tvEmail.setText("Email: " + uInfo.getEmail());
            //tvPassword.setText("Password: " + uInfo.getPassword());


        }
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
*/
}
