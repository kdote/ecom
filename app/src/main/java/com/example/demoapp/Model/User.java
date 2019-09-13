package com.example.demoapp.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("password")
    private String password;

//    public User(String firstname, String lastname, String email, String address, String city, String telephone, String password) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.email = email;
//        this.address = address;
//        this.city = city;
//        this.telephone = telephone;
//        this.password = password;
//    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }
}
