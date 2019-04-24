package com.example.ezydonate;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public abstract class FireBaseWrapper extends AppCompatActivity
{
    Boolean CreateNewUser(final FirebaseAuth mAuth, String email, String password)
    {
        if(email.trim().equals("") || password.trim().equals(""))
        {
            Toast.makeText(null, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        Toast.makeText(FireBaseWrapper.this,"Creating...", Toast.LENGTH_SHORT).show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(FireBaseWrapper.this,"Successful Creation", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(FireBaseWrapper.this,"An error has occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return true;
    }
    /*
    public Boolean signIn(FirebaseAuth mAuth ,String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(null, "signInWithEmail:success", Toast.LENGTH_SHORT);
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    */
}
