package com.example.mobilewallet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    private TextView Welcome;
    private CardView Balance;
    private CardView SendMoney;
    private CardView ViewStatement;
    private CardView Profile;
    private CardView Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Welcome = findViewById(R.id.tvWelcome);
        Balance = findViewById(R.id.cdBalance);
        SendMoney = findViewById(R.id.cdSendMoney);
        ViewStatement = findViewById(R.id.cdViewStatement);
        Profile = findViewById(R.id.cdProfile);
        Logout = findViewById(R.id.cdLogout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, BalancePage.class);
                startActivity(intent);
            }
        });
        SendMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, SendmoneyPage.class);
                startActivity(intent);
            }
        });
        ViewStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, ViewStatementPage.class);
                startActivity(intent);
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, ProfilePage.class);
                startActivity(intent);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Login.class);
                startActivity(intent);
            }
        });
    }
}