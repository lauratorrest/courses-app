package com.company.courses.authentication.shared.utils;

import java.util.HashSet;
import java.util.Set;

public class AppUtil {

    public static final String APP_MAIN_PATH = "/auth";
    public static final String SIGN_UP_PATH = "/signup";
    public static final String LOGIN_PATH = "/login";
    public static final String FORGOT_PASSWORD_PATH = "/forgotPassword";
    public static final String ACTIVE_STATUS = "ACTIVE";

    public static final Set<String> VALID_PATHS = new HashSet<>();

    static {
        VALID_PATHS.add(APP_MAIN_PATH + SIGN_UP_PATH);
        VALID_PATHS.add(APP_MAIN_PATH + LOGIN_PATH);
        VALID_PATHS.add(APP_MAIN_PATH + FORGOT_PASSWORD_PATH);
    }
}
