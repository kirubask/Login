package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView doUSignIn;
    EditText fName;
    EditText rEmail;
    EditText rPassword;
    EditText rePasswordd;
    Button rButton;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://login-70d7a-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        doUSignIn = findViewById(R.id.doUSignIn);
        fName = findViewById(R.id.et_name);
        rEmail = findViewById(R.id.et_email);
        rPassword = findViewById(R.id.et_password);
        rePasswordd = findViewById(R.id.et_repassword);
        rButton = findViewById(R.id.btn_register);
        doUSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, SignUp.class);
                startActivity(login);
            }
        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fName.getText().toString().trim();
                String mail = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();
                String rePassword = rePasswordd.getText().toString().trim();


                if (name.isEmpty() || mail.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    System.out.println(password);
                    System.out.println(rePassword);
                    Log.d("pass", password);

                    Toast.makeText(MainActivity.this, "please fill all the fields!", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(rePassword)) {
                    Toast.makeText(MainActivity.this, "passwords are not matching!", Toast.LENGTH_SHORT).show();
                } else {


                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

//                            if (snapshot.getChildrenCount() <0 ){
//                                databaseReference.child("Users").child(name).child("FullName").setValue(name);
//                                databaseReference.child("Users").child(name).child("Email").setValue(mail);
//                                databaseReference.child("Users").child(name).child("Password").setValue(password);
//                            }else{
//                                databaseReference.child("Users").child(name).child("FullName").setValue(name);
//                                databaseReference.child("Users").child(name).child("Email").setValue(mail);
//                                databaseReference.child("Users").child(name).child("Password").setValue(password);
//
//                                Toast.makeText(MainActivity.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();
//                            }

                            if (snapshot.hasChild(name)) {
                                System.out.println("  checking2...");

                                System.out.println(name);
                                Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                            } else {
                                System.out.println("  checking1...");
                                databaseReference.child("Users").child(name).child("FullName").setValue(name);
                                databaseReference.child("Users").child(name).child("Email").setValue(mail);
                                databaseReference.child("Users").child(name).child("Password").setValue(password);

                                Toast.makeText(MainActivity.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();
                            }

//                            snapshot.notify(databaseReference);
//
//                           databaseReference.child("Users").child(name).child("FullName").setValue(name);
//                           databaseReference.child("Users").child(name).child("Email").setValue(mail);
//                           databaseReference.child("Users").child(name).child("Password").setValue(password);
//
//                           Toast.makeText(MainActivity.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MainActivity.this, "error from firebase ondatachange", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });


    }
}