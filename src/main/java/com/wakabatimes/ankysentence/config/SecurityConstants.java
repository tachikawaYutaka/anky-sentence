package com.wakabatimes.ankysentence.config;

public class SecurityConstants {
    public static final String SECRET = "a1hHRU8kRNcqFaWO";
    public static final long EXPIRATION_TIME = 28_800_000; // 8hours
    public static final String TOKEN_PREFIX = "kagomekagomekagononakanotori ";
    public static final String HEADER_STRING = "auth";
    public static final String SIGNUP_URL = "/user/signup";
    public static final String LOGIN_URL = "/user/login";
    public static final String LOGIN_ID = "userName"; // defalut:username
    public static final String PASSWORD = "userPassword"; // default:password
}
