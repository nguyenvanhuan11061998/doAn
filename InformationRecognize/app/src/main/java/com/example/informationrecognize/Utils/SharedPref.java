package com.example.informationrecognize.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences shared;
    SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        shared = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        editor = shared.edit();
    }

    public enum KeyShared {
        INUSER
    }

    public String getValue(KeyShared k, String defaultValue) {
        return shared.getString(k.toString(), defaultValue);
    }

    public void setValue(KeyShared k, String value) {
        editor.putString(k.toString(), value);
        editor.commit();
    }
}
