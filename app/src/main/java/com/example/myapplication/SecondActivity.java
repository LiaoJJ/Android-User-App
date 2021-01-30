package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.service.ResetService;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText text_username = (EditText)findViewById(R.id.email);
        EditText text_password = (EditText)findViewById(R.id.password);
        Button reset_password = (Button)findViewById(R.id.reset_password);
        Button logout = (Button)findViewById(R.id.logout);
        TextView text_message = (TextView)findViewById(R.id.message1);

        Intent intent = getIntent();
        String email = intent.getStringExtra("MESSAGE");
        text_username.setText(email);
        text_username.setEnabled(false);

        ResetService resetService = new ResetService(this);

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = text_password.getText().toString();
                resetService.reset(email, password);
                finish();
            }
        });
    }

    public void back1(View v) {
        //关闭当前界面
        finish();
    }
}
