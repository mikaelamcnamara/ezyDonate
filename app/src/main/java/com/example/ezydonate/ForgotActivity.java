package com.example.ezydonate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends Activity {

    DrawerLayout dmLayout;

    private FirebaseAuth mAuth;
    static final int requestcode = 1;

    //lmaoooooo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.forgot_user_password);
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
    }

    public void reset_password(final FirebaseAuth mAuth, final String email) {
        if (email.trim().equals("")) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }

        else {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotActivity.this, "Password Reset Request Sent, Check Your Email", Toast.LENGTH_SHORT).show();

                                Intent main = new Intent(ForgotActivity.this, MainActivity.class);
                                startActivity(main);

                            } else {
                                Toast.makeText(ForgotActivity.this, "Email Address Does Not Exist",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    }

    public void forget_password(View view) {

        EditText email = (EditText) findViewById(R.id.editText8);

        reset_password(mAuth, email.getText().toString());
    }

    public void cancel(View view) {

        finish();
    }
}
