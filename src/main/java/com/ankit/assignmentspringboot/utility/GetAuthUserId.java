package com.ankit.assignmentspringboot.utility;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class GetAuthUserId {
    public static Integer getUserId(){
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        try {
            return Integer.parseInt(authentication.getName());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
