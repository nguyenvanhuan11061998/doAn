package com.example.informationrecognize.base.baseApi;

public class ApiUtils {
    public static final String BASE_URL = "http://10.0.2.2/mediaManager/";
//    public static final String BASE_URL = "http://192.168.20.31/mediaManager/";

    public static ApiRepository getDataApi(){
        return ApiBuilder.getApi(BASE_URL).create(ApiRepository.class);
    }
}
