package com.ankit.assignmentspringboot.utility;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class GetAuthUserId {
    public static Integer getUserId(){
        return Integer.parseInt(Objects.requireNonNull(SecurityContextHolder.getContext()
                        .getAuthentication())
                .getName());
    }
}
