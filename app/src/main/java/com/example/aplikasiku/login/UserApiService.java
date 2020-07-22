package com.example.aplikasiku.login;

import com.example.aplikasiku.signup.SignupBody;
import com.example.aplikasiku.signup.SignupResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {
    @POST("login")
    Call<LoginResult> getResultInfo(@Body LoginBody loginBody);

    @POST("api/user")
    Call<SignupResult> signupUser(@Body SignupBody signupBody);
}

