package com.denaay.pages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.denaay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText textEmail, textPassword, textConfirm;
    private ProgressDialog pDialog;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        textEmail = (EditText) findViewById(R.id.email);
        textPassword = (EditText) findViewById(R.id.password);
        textConfirm = (EditText) findViewById(R.id.confirmationPassword);

        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();
                String confirm_password = textConfirm.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                displayLoader();

                if(!confirm_password.equals(password)){
                    Toast.makeText(getApplicationContext(), "Password and Confirmation Password does'nt match!", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                }else{
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pDialog.dismiss();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Create Account...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

}
