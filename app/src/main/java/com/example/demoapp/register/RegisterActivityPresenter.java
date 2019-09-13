package com.example.demoapp.register;

import android.util.Patterns;

import com.example.demoapp.Model.User;
import com.example.demoapp.network.ApiInterface;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivityPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;

    private ApiInterface api;

    public RegisterActivityPresenter(RegisterContract.View view, ApiInterface api) {
        this.view = view;
        this.api = api;
    }

    public void register() {
        view.showRegisterProgressBar();
        HashMap<String, Object> map = new HashMap<>();
        map.put("firstname", view.getUserFirstname());
        map.put("lastname", view.getUserLastname());
        map.put("email", view.getUserEmail());
        map.put("address", view.getUserAddress());
        map.put("city", view.getUserCity());
        map.put("telephone", view.getUserTelephone());
        map.put("password", view.getUserPassword());

        Call<JsonObject> registerUser = api.registerUser(map);
        registerUser.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject json = response.body();
                view.hideRegisterProgressBar();
                view.registrationSuccess();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                view.registrationError();
            }
        });
    }



    public boolean validateFirstname() {
        if (view.getUserFirstname().isEmpty()){
            view.setErrorMessage(1, "Enter Your Firstname");
            return false;
        }
        else{
            view.setErrorMessage(1, null);
            return true;
        }
    }

    public boolean validateLastname() {
        if (view.getUserLastname().isEmpty()){
            view.setErrorMessage(2, "Enter Your Lastname");
            return false;
        }
        else{
            view.setErrorMessage(2, null);
            return true;
        }
    }

    public boolean validateEmail() {
        if (view.getUserEmail().isEmpty()){
            view.setErrorMessage(3, "Enter Your Email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(view.getUserEmail()).matches()){
            view.setErrorMessage( 3,"Enter correct Email address");
            return false;
        }
        else{
            view.setErrorMessage(3, null);
            return true;
        }
    }

    public boolean validateAddress() {
        if (view.getUserAddress().isEmpty()){
            view.setErrorMessage(4, "Enter Your Address");
            return false;
        }
        else{
            view.setErrorMessage(4, null);
            return true;
        }
    }

    public boolean validateCity() {
        if (view.getUserCity().isEmpty()){
            view.setErrorMessage(5, "Enter Your City");
            return false;
        }
        else{
            view.setErrorMessage(5, null);
            return true;
        }
    }

    public boolean validateTelephone(){
        if (view.getUserTelephone().isEmpty()){
            view.setErrorMessage(6, "Enter Your Lastname.");
            return false;
        }
        else if (view.getUserTelephone().length() > 10){
            view.setErrorMessage(6, "Telephone number too long.");
            return false;
        }
        else{
            view.setErrorMessage(6, null);
            return true;
        }
    }

    public boolean validatePassword() {
        if (view.getUserPassword().isEmpty()){
            view.setErrorMessage(7, "Enter Your Password.");
            return false;
        }
        else if (!view.getUserPassword().equals(view.getConfirmPassword())){
            view.setErrorMessage(7, "Password and Confirm aren't equal.");
            return false;
        }
        else{
            view.setErrorMessage(7, null);
            return true;
        }

    }

    public boolean validateConfirmPassword() {
        if (view.getUserPassword().isEmpty()){
            view.setErrorMessage(8, "Enter Confirm Password.");
            return false;
        }
        else if (!view.getUserPassword().equals(view.getConfirmPassword())){
            view.setErrorMessage(8, "Password and Confirm aren't equal.");
            return false;
        }
        else{
            view.setErrorMessage(8, null);
            return true;
        }
    }
//    public boolean isLayoutValid(List<TextInputLayout> textInputLayoutList) {
//        boolean isValid = false;
//        for(int index = 0 ; index < textInputLayoutList.size(); index++){
//           if(textInputLayoutList.get(index).getEditText().toString().equals("")){
//               isValid = false;
//               view.setError(textInputLayoutList.get(0));
//           }else {
//
//               isValid = true;
//           }
//        }
//        return isValid;

//    }

//    @Override
//    public void register() {
//

//    }

    @Override
    public void confirmValidation() {
        if (!validateFirstname() | !validateLastname() | !validateEmail() | !validateAddress() | !validateCity() |
        !validateTelephone() | !validatePassword() | !validateConfirmPassword()){
            return;
        }

        register();
    }

}
