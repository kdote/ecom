package com.example.demoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.demoapp.base.BaseActivity;
import com.example.demoapp.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment());
    }

    public static Intent createIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}
