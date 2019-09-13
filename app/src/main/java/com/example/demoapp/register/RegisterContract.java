package com.example.demoapp.register;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public interface RegisterContract {

    interface Presenter{
//        boolean isLayoutValid(List<TextInputLayout> textInputLayoutList);
//
//        void register();

        void confirmValidation();
//        void register();
    }

    interface View{
        void showRegisterProgressBar();
        void hideRegisterProgressBar();
        void registrationSuccess();
        void registrationError();

        String getUserFirstname();
        String getUserLastname();
        String getUserEmail();
        String getUserAddress();
        String getUserCity();
        String getUserTelephone();
        String getUserPassword();
        String getConfirmPassword();

        void setErrorMessage(int inputLayout, String message);

//        void setError(TextInputLayout textInputLayout);
    }

}
