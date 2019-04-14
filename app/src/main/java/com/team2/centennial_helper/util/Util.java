package com.team2.centennial_helper.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final static String MyPREFERENCES = "CENTENNIAL_HELPER";

    public static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(Util.MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public static void setSharedPref(Context context,int value) {
        SharedPreferences.Editor editor = Util.getSharedPreferences(context).edit();
        editor.putInt("user_type", value);
        editor.apply();
    }



}
