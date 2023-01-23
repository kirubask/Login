package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    TextView signIn;
    EditText  eMailTxt;
    EditText passWordTxt;
    Button loginBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        signIn = findViewById(R.id.dontAccSignUp);
        eMailTxt = findViewById(R.id.login_email);
        passWordTxt = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.loginBtn);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInn = new Intent(SignUp.this,MainActivity.class);
                startActivity(signInn);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = eMailTxt.getText().toString().trim();
                String passWord = passWordTxt.getText().toString().trim();

                if (email.isEmpty() && (passWord.isEmpty())){
                    Toast.makeText(getApplicationContext(), "please enter the value!", Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });





    }
}