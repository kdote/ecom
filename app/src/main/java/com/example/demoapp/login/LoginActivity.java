package com.example.demoapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.demoapp.R;
import com.example.demoapp.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static Intent createIntent(Context context){
        Intent loginIntent = new Intent(context, LoginActivity.class);
        return loginIntent;
    }
}
