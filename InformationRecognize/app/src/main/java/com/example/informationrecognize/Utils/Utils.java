package com.example.informationrecognize.Utils;

public class Utils {
    public static boolean stringNullOrEmpty(String text){
        if (text == null || 0 == text.length() || "null".equals(text)){
            return true;
        } else {
            return false;
        }
    }
}
