package com.example.mobilewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendmoneyPage extends AppCompatActivity {

    private TextView Money_Info;
    private EditText AccountNo;
    private  EditText Amount;
    private Button Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmoney_page);

        Money_Info = findViewById(R.id.tvMoney_info);
        AccountNo = findViewById(R.id.etAccount);
        Amount = findViewById(R.id.etAmount);
        Send = findViewById(R.id.btnSend);
    }
}