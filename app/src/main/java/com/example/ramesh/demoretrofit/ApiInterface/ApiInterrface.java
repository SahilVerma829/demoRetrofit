package com.example.ramesh.demoretrofit.ApiInterface;

import com.example.ramesh.demoretrofit.ApiResponse.LoginResponse;
import com.example.ramesh.demoretrofit.ApiResponse.Responser;
import com.example.ramesh.demoretrofit.ApiResponse.UpdateResponse;
import com.example.ramesh.demoretrofit.ApiResponse.ViewResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterrface {

    @FormUrlEncoded
    @POST("completefiles.php")
    Call<Responser> registration(
            @Field("register") String tag,
            @Field("name") String name,
            @Field("last_name") String lastname,
            @Field("email") String email,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("password") String password,
            @Field("contect") String contect);

    //login
    @FormUrlEncoded
    @POST("completefiles.php")
    Call<LoginResponse> login(
            @Field("login") String login,
            @Field("email") String email,
            @Field("password") String password );

    //update
    @FormUrlEncoded
    @POST("completefiles.php")
    Call<UpdateResponse> update(
            @Field("update") String tag,
            @Field("id") String id,
            @Field("name") String name,
            @Field("last_name") String lastname,
            @Field("email") String email,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("password") String password,
            @Field("contect") String contect);

   // ViewUser
    @FormUrlEncoded
    @POST("completefiles.php")
    Call<ViewResponse> view(
      @Field("viewusers") String tag
    );
}
