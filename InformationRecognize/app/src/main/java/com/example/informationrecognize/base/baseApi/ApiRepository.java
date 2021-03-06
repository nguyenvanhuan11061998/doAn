package com.example.informationrecognize.base.baseApi;

import android.net.Uri;

import com.example.informationrecognize.login.loginAccount.model.LoginAccount;
import com.example.informationrecognize.main.checkIn.checkInStudent.model.ListStudentCheckIn;
import com.example.informationrecognize.main.checkIn.infoStudent.model.CheckInResponse;
import com.example.informationrecognize.main.checkIn.mvvm.model.ExamRoomModel;
import com.example.informationrecognize.main.homeFunction.model.BannerHomeResponse;
import com.example.informationrecognize.main.other.changePassword.model.ChangePasswordModel;

import io.reactivex.Observable;
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
    Call<LoginAccount> getUserData(@Field("idUser") String userId);

    @FormUrlEncoded
    @POST("changePassword.php")
    Call<ChangePasswordModel> changePasword(@Field("username") String username,
                                            @Field("old_password") String password,
                                            @Field("new_password") String newPassword);

    @FormUrlEncoded
    @POST("getListExamRoom.php")
    Call<ExamRoomModel> getListExamRoom(@Field("idUser") String idUser);

    @FormUrlEncoded
    @POST("getListStudentCheckIn.php")
    Call<ListStudentCheckIn> getListStudentCheckIn(@Field("idExamRoom") String idExamRoom);

    @FormUrlEncoded
    @POST("checkIn.php")
    Call<CheckInResponse> checkInStudent(@Field("idStudent") String idStudent,
                                         @Field("idExamRoom") String idExamRoom);

    @FormUrlEncoded
    @POST("checkInByAI.php")
    Call<CheckInResponse> checkInStudentByAI(@Field("uri") Uri uri,
                                             @Field("idExamRoom") String idExamRoom);

    @POST("listBannerHome.php")
    Observable<BannerHomeResponse> getListBannerHome();
}
