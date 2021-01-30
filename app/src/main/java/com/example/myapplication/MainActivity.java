package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Enum.Message;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.service.LoginService;
import com.example.myapplication.service.ResetService;
import com.example.myapplication.service.SignupService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text_username = (EditText)findViewById(R.id.email);
        EditText text_password = (EditText)findViewById(R.id.password);
        Button signup = (Button)findViewById(R.id.sign_up);
        Button login = (Button)findViewById(R.id.login);
        TextView text_message = (TextView)findViewById(R.id.message1);

        LoginService loginService = new LoginService(this);
        SignupService signupService = new SignupService(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = text_username.getText().toString();
                String password = text_password.getText().toString();
                Message message = signupService.signup(username, password);
                text_message.setText(message.getText());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = text_username.getText().toString();
                String password = text_password.getText().toString();
                Message message = loginService.login(username, password);
                if(Message.LOGIN_SUCCESS==message){
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("MESSAGE", username);
                    startActivityForResult(intent, 2);
                }
                text_message.setText(message.getText());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView text_message = (TextView) findViewById(R.id.message1);
        EditText text_password = (EditText)findViewById(R.id.password);
        text_message.setText("Please Login");
        text_password.setText("");
    }

    public void testCreatedDB(View v){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Log.e("TAG", "DB created");
//        dbHelper.close();
    }
    public void testQueryDB(){
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("USER", null, "email=?", new String[]{"admin"}, null, null, null);
        Log.e("TAG", ""+cursor.getCount());
        cursor.moveToFirst();
        Log.e("TAG", cursor.getString(0));
        database.close();
    }
}