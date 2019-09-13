package com.example.demoapp;

import android.app.Application;

import com.example.demoapp.di.component.AppComponent;
import com.example.demoapp.di.component.DaggerAppComponent;
import com.example.demoapp.di.module.AppModule;
import com.example.demoapp.di.module.NetModule;
import com.example.demoapp.network.Configuration;

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Configuration.getBaseUrl()))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
