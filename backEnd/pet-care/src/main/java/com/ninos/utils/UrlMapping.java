package com.ninos.utils;

public class UrlMapping {

    public static final String API="/api";
    public static final String USERS= API + "/users";
    public static final String REGISTER_USER= "/register";
    public static final String UPDATE_USER= "/update/{userId}";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String DELETE_USER_BY_ID ="/delete/{userId}";
    public static final String GET_ALL_USERS = "/all-users";
}
