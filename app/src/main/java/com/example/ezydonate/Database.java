/*
package com.example.ezydonate;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Database extends FireBaseWrapper {

    Database() {

    }

    public Boolean CreateNewUser(final FirebaseAuth mAuth, String email, String password)
    {


        if(email.trim().equals("") || password.trim().equals(""))
        {
            Toast.makeText(null, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(Database.this,"Creating...", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Database.this,"Successful Creation", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Database.this,"An error has occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return true;
    }
}
*/
