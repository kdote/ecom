package com.example.demoapp.login;

import android.content.SharedPreferences;
import android.util.Patterns;

import com.example.demoapp.model.Auth;
import com.example.demoapp.network.ApiInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {

    LoginActivityContract.View view;
    private ApiInterface api;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public LoginActivityPresenter(LoginActivityContract.View view, ApiInterface api, SharedPreferences preferences, SharedPreferences.Editor editor) {
        this.view = view;
        this.api = api;
        this.preferences = preferences;
        this.editor = editor;
    }


    private void authentication() {
        view.showLoginProgressBar();
        Call<Auth> loginUser = api.login(view.getInputEmail(), view.getInputPassword());
        loginUser.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                Auth auth = response.body();
                if (auth.isSuccess()){
                    saveLoginDetails(view.isRemembered(), auth);
                    view.loginSuccess();
                }
                else{
                    view.loginError("Incorrect email/ password");
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                view.loginError("Can't connect to the server.");
            }
        });
    }

    private void saveLoginDetails(boolean remembered, Auth auth) {
        Gson gson = new Gson();
        String json = gson.toJson(auth);
        editor.putString(LoginActivity.AUTH, json);
        editor.putBoolean(LoginActivity.IS_REMEMERED, remembered);
        editor.putBoolean(LoginActivity.IS_LOGGEDIN, true);
        editor.commit();
    }

    private boolean validateEmail(){
        if (view.getInputEmail().isEmpty()){
            view.setErrorMessage(1, "Enter Your Email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(view.getInputEmail()).matches()){
            view.setErrorMessage( 1,"Enter correct Email address");
            return false;
        }
        else{
            view.setErrorMessage(1, null);
            return true;
        }
    }

    private boolean validatePassword(){
        if (view.getInputPassword().isEmpty()){
            view.setErrorMessage(2, "Enter Your Password.");
            return false;
        }
        else{
            view.setErrorMessage(2, null);
            return true;
        }
    }

    @Override
    public boolean isRemembered() {
        return preferences.getBoolean(LoginActivity.IS_REMEMERED, false);
    }

    @Override
    public boolean isLoggedIn() {
        return preferences.getBoolean(LoginActivity.IS_LOGGEDIN, false);
    }

    @Override
    public Auth getAuth() {
        String json = preferences.getString(LoginActivity.AUTH, "");
        if (json.equals("")) return null;
        Gson gson = new Gson();
        Auth auth = gson.fromJson(json, Auth.class);
        return auth;
    }


    @Override
    public void confirmValidation() {
        if (!validateEmail() | !validatePassword()){
            return;
        }
        authentication();
    }


}
