package com.example.informationrecognize.Utils;

import com.example.informationrecognize.R;
import com.example.informationrecognize.login.loginAccount.model.UserLogin;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

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

    public static final DisplayImageOptions optionAvatar = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .showImageOnLoading(R.drawable.default_avatar_theme1)
            .showImageForEmptyUri(R.drawable.default_avatar_theme1)
            .showImageOnFail(R.drawable.default_avatar_theme1)
            .build();
}
