package com.example.demoapp.login;

import com.example.demoapp.model.Auth;
import com.google.android.material.textfield.TextInputLayout;

public interface LoginActivityContract {
    interface Presenter{
        boolean isRemembered();
        boolean isLoggedIn();
        Auth getAuth();
        void confirmValidation();
    }

    interface View{
        String getInputEmail();
        String getInputPassword();
        boolean isRemembered();

        void showColorToastMessage(String message);
        void showLoginProgressBar();
        void hideLoginProgressBar();
        void loginError(String message);
        void setErrorMessage(int inputLayout, String message);
        void loginSuccess();
    }
}
