package com.team2.centennial_helper.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    // Write a message to the database
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static boolean isValidEmail(String email){

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    public static boolean isValidPw(String password){

        if(password.length() > 6){
            return true;
        }
        else {
            return false;
        }
    }



}
