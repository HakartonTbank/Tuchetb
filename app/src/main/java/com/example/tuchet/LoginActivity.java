package com.example.tuchet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText login;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_activity);
        login = findViewById(R.id.plain_text_login);
        pass = findViewById(R.id.plain_text_pass);
    }



    public void toMain(View view) {

        if (login.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
            Toast.makeText(this, "Заполните все поля.",Toast.LENGTH_LONG).show();
        }else {
            //тут всякая дичь с бд




            //это переход это не трогай
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
