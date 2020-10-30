package com.example.informationrecognize.Utils;

import com.example.informationrecognize.login.loginAccount.model.UserLogin;

public class Utils {
    public static volatile UserLogin userLogin;

    public static boolean stringNullOrEmpty(String text){
        if (text == null || 0 == text.length() || "null".equals(text)){
            return true;
        } else {
            return false;
        }
    }

    public static UserLogin getUserLogin() {
        return userLogin;
    }

    public static void setUserLogin(UserLogin userLogin) {
        Utils.userLogin = userLogin;
    }

    public static void clearUserModel() {
        userLogin = null;
    }
}
