package com.ankit.assignmentspringboot.utility;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN,
    USER,
    MODERATOR;

    @JsonCreator
    public static UserRole from(String value) {
        return UserRole.valueOf(value.toUpperCase());
    }
}
