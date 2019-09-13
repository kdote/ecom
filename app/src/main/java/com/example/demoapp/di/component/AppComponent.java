package com.example.demoapp.di.component;

import com.example.demoapp.di.module.AppModule;
import com.example.demoapp.di.module.NetModule;
import com.example.demoapp.register.RegisterActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(RegisterActivity target);
}
