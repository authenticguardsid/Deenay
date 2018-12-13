package com.denaay.pages;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.denaay.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private static final String NAME_KEY = "Name";
    private static final String EMAIL_KEY = "Email";
    private static final String PHONE_KEY = "Phone";

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private EditText fullname, email, phone;
    private Button save;
    private String current_user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseFirestore= FirebaseFirestore.getInstance();

        fullname = (EditText)findViewById(R.id.edt_fullname);
        email = (EditText)findViewById(R.id.edt_email);
        phone = (EditText)findViewById(R.id.edt_phone);
        save = (Button) findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_user();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        current_user =firebaseAuth.getCurrentUser().getUid();
    }

    public void add_user(){

        String nama_user = fullname.getText().toString().trim();
        String email_user = email.getText().toString().trim();
        String phone_user = phone.getText().toString().trim();

        Map<String, Object>  newuser = new HashMap<>();
        newuser.put(NAME_KEY, nama_user);
        newuser.put(EMAIL_KEY, email_user);
        newuser.put(PHONE_KEY, phone_user);
        firebaseFirestore.collection("Users").document(current_user).set(newuser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditProfileActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, MasterActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileActivity.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }

}
