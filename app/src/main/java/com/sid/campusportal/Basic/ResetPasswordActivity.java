package com.sid.campusportal.Basic;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.sid.campusportal.R;


import static android.widget.Toast.makeText;

public class  ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = findViewById(R.id.email);
        btnReset = findViewById(R.id.btn_reset_password);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    makeText(getApplicationContext(), "Enter your registered email", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_LONG).show();
                        } else {
                            makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}
