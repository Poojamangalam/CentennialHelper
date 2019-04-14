package com.team2.centennial_helper;

import com.google.firebase.auth.FirebaseAuth;
import com.team2.centennial_helper.unit_test.Auth;
import com.team2.centennial_helper.unit_test.Util;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void check_correctEmail() {
        assertTrue(Util.isValidEmail("pmangla@my.centennialcollege.ca"));
    }

    @Test
    public void check_invalidEmail() {
        assertFalse(Util.isValidEmail("pmangla@my"));
    }

    @Test
    public void check_correctPw() {
        assertTrue(Util.isValidPw("password123"));
    }

    @Test
    public void check_invalidPw() {
        assertFalse(Util.isValidPw("pw123"));
    }

    @Test
    public void check_loggedInUser(){

        FirebaseAuth firebaseAuth = Mockito.mock(FirebaseAuth.class);
        Mockito.when(firebaseAuth.getUid()).thenReturn("123456789");

        Auth auth = new Auth(firebaseAuth);
        assertTrue(auth.getId());
    }

    @Test
    public void check_notLoggedInUser(){

        FirebaseAuth firebaseAuth = Mockito.mock(FirebaseAuth.class);
        Mockito.when(firebaseAuth.getUid()).thenReturn("");

        Auth auth = new Auth(firebaseAuth);
        assertFalse(auth.getId());
    }

    /* newly added unit tests*/

    @Test
    public void check_trueSearchContainsMethod(){

        String key = "-LbQSt9j29qZtvVrbM89";
        String subKey = "vVr";
        assertTrue(Util.isKeyContainsValue(key,subKey));
    }

    @Test
    public void check_falseSearchContainsMethod(){

        String key = "-LbQSt9j29qZtvVrbM89";
        String subKey = "ABCD";
        assertFalse(Util.isKeyContainsValue(key,subKey));
    }

    @Test
    public void check_isNonEmptyAnyString(){

        String studentNumber = "1234567890";
        String discription = "discription";
        String programName = "Software Engineering Technician";
        String courseName = "Programming 3";
        String ticketType = "Finanace";

        String[] values = new String[5];

        values[0] = studentNumber;
        values[1] = discription;
        values[2] = programName;
        values[3] = courseName;
        values[4] = ticketType;

        assertFalse(Util.isAnyEmptyString(values));
    }

    @Test
    public void check_isEmptyAnyString(){

        String studentNumber = "1234567890";
        String discription = "";
        String programName = "Software Engineering Technician";
        String courseName = "Programming 3";
        String ticketType = "";

        String[] values = new String[5];

        values[0] = studentNumber;
        values[1] = discription;
        values[2] = programName;
        values[3] = courseName;
        values[4] = ticketType;

        assertTrue(Util.isAnyEmptyString(values));
    }

    @Test
    public void check_isCentennialEmial(){
        assertTrue(Util.isCentennialEmail("pmangla@my.centennialcollege.ca"));
    }

    @Test
    public void check_isNotCentennialEmial(){
        assertFalse(Util.isCentennialEmail("pmangla@gmail.com"));
    }
    /*newly added unit tests ends*/
}