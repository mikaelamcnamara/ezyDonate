package com.example.ezydonate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ezydonate.Model.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountFragment extends Fragment {

    private static final String TAG = "MainActivity";


    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private UserInformation uInfo;



    @BindView(R.id.editText10) EditText etEmail;

    @BindView(R.id.editText14)EditText etFullname;

    @BindView(R.id.editText12) EditText etPassword;


    public AccountFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity();
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
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        myRef = mFirebaseDatabase.getReference("User/" + userID+"/");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {

                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }

            }
        };

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

            uInfo = new UserInformation();
            uInfo.setEmail(dataSnapshot.child("email").getValue().toString());
            uInfo.setFullname(dataSnapshot.child("fullname").getValue().toString());



            etEmail.setText(uInfo.getEmail());
            etFullname.setText(uInfo.getFullname());



    }

    @OnClick(R.id.textView17)public void updateEmail(){
        if(etEmail.getText().toString().trim().equals("")){
            toastMessage("Please enter new email.");
        }else {
            myRef.child("email").setValue(etEmail.getText().toString());
            getActivity().getFragmentManager().popBackStack();
            toastMessage("Email successfully updated.");
        }
    }

    @OnClick(R.id.textView24)public void updateFullname(){
        if(etFullname.getText().toString().trim().equals("")){
            toastMessage("Please enter new name.");
        }else {
            myRef.child("fullname").setValue(etFullname.getText().toString());
            getActivity().getFragmentManager().popBackStack();
            toastMessage("Full Name successfully updated.");
        }
    }
    @OnClick(R.id.textView20)public void updatePassword(){
        if(etPassword.getText().toString().trim().equals("")){
            toastMessage("Please enter new password.");
        }else {
            mAuth.getCurrentUser().updatePassword(etPassword.getText().toString());
            toastMessage("Password successfully updated.");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

}
