package com.ankit.assignmentspringboot.utility;

public class CONSTANTS {
    public static final String GetAllUsersKey = "allUsers";
    public static Enum UserRole;

    public static String getUserRedisKey(String id) {
        // user:101
        return "user:" + id;
    }

    public static String getUserAddressRedisKey(Integer id) {
        // ua:101
        return "ua:" + id;
    }

    public static String getCompanyRedisKey(Integer id) {
        // com:101
        return "com:" + id;
    }

    public static String getCompanyByUserIdRedisKey(String id) {
        // uc:101
        return "uc:" + id;
    }
}
