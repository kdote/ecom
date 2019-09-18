package com.example.demoapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public static final String LOGIN_PREF = "loginPref";

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences proviedSharedPreference(Context context){
        return context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    public SharedPreferences.Editor providePrefEditor(SharedPreferences preferences){
        return preferences.edit();
    }
}
