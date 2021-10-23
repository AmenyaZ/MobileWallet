package com.example.mobilewallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private TextView About;
    private EditText ID;
    private EditText Pin;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        About = findViewById(R.id.tvAbout);
        ID = findViewById(R.id.etID);
        Pin = findViewById(R.id.etpin);
        Login = findViewById(R.id.btnLogIn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Homepage.class);
            }
        });
    }
}