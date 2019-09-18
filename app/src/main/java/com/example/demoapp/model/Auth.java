package com.example.demoapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Auth {
    @SerializedName("user")
    private List<User> user;
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
