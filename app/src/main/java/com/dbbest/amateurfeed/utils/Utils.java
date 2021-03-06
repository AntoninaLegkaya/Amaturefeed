package com.dbbest.amateurfeed.utils;

import java.util.regex.Pattern;

/**
 * Created by antonina on 19.01.17.
 */

public class Utils {
    public static final String TAG_LOG = "Log";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "(" +
                    "(?=.*\\d)" +

                    "(?=.*[a-z])" +

                    "." +

                    "{6,20}" +
                    ")"
    );

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9а-яА-Я ёЁ]+$"

    );
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^(" +
                    "(\\+3|8|\\+7|)[\\- ]?)" +
                    "?(" +
                    "\\(?" +
                    "\\d{3}" +
                    "\\)" +
                    "?[\\- ]?" +
                    ")" +
                    "?[\\d\\- ]" +
                    "{7,10}" +
                    "$"
    );

    public static boolean isEmailValid(String email) {

        return EMAIL_PATTERN.matcher(email).matches();

    }

    public static boolean isPasswordLengthValid(String password) {

        return (password.length() > 6);
    }

    public static boolean isPasswordValid(String password) {

//        return PASSWORD_PATTERN.matcher(password).matches();
        return true;
    }

    public static boolean isFullNameValid(String firstName) {

        return NAME_PATTERN.matcher(firstName).matches();
    }

    public static boolean isPhoneValid(String phone) {

        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isAddressValid(String address) {
        return false;
    }

    public static boolean isDeviceIdValid(String deviceId) {
        return false;
    }

    public static boolean isOsTypeValid(String osType) {
        return false;
    }

    public static boolean isDeviceTokenValid(String deviceToken) {
        return false;
    }
}
