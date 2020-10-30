package com.example.informationrecognize.base.baseApi;

import com.example.informationrecognize.login.loginAccount.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRepository {
    @FormUrlEncoded
    @POST("login.php")
    Call<UserLogin> loginAccount(@Field("username") String username,
                                 @Field("userpassword") String password);
}
