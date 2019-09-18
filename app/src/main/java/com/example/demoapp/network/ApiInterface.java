package com.example.demoapp.network;


import com.example.demoapp.model.Auth;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register_customer_api.php")
    Call<JsonObject> registerUser(@FieldMap HashMap<String, Object> userData);

    @FormUrlEncoded
    @POST("get_customer_detail_api.php")
    Call<Auth> login(@Field("email") String email, @Field("password") String password);
}
