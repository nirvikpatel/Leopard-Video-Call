package com.nirvik.leopardvideocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup_activity extends AppCompatActivity {
    TextView username,email,password;
    FirebaseAuth mauth;
    FirebaseDatabase database;
    Button signupbtn,loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        username=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signupbtn=findViewById(R.id.login);
        mauth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        loginbtn=findViewById(R.id.signup);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(signup_activity.this, "Thank You", Toast.LENGTH_SHORT).show();
                            User user=new User(username.getText().toString(),email.getText().toString());
                            database.getReference().child("users").push().setValue(user);
                            Intent intent=new Intent(signup_activity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(signup_activity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signup_activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}