package com.example.android.projectlattice.NetworkUtils;

import android.service.autofill.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostmanService {

    @POST("data/signUp")
    @FormUrlEncoded
    Call<UserData> saveUserData(@Field("name") String name,
                                @Field("address") String address,
                                @Field("email") String email,
                                @Field("phone_no") String phoneNo,
                                @Field("password")String password);
    @GET("data/user")
    Call<UserData> getUserData();
}
