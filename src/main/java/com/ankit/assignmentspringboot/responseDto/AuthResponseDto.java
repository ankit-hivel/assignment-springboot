package com.ankit.assignmentspringboot.responseDto;

import com.ankit.assignmentspringboot.utility.UserRole;

public class AuthResponseDto {
    private UserRole role;

    public AuthResponseDto(UserRole role){
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
