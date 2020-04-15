package com.example.androidlogin.Retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestService
{
    @GET("get")
    Call<String> requestGet();

    @POST("register")
    @FormUrlEncoded
    Call<Profile> createUser(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("phone") String phone );

    @POST("login")
    @FormUrlEncoded
    Call<Profile> loginUser(@Field("email") String email,
                           @Field("password") String password);
}
