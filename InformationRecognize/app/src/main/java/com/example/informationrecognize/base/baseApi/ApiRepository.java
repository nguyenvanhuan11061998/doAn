package com.example.informationrecognize.base.baseApi;

import com.example.informationrecognize.login.loginAccount.model.LoginAccount;
import com.example.informationrecognize.login.loginAccount.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRepository {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginAccount> loginAccount(@Field("username") String username,
                                    @Field("userpassword") String password);

    @FormUrlEncoded
    @POST("getUserData.php")
    Call<LoginAccount> getUserData(@Field("id_user") String userId);
}
