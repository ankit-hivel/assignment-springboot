package com.ankit.assignmentspringboot.utility.security;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.utility.GetAuthUserId;
import com.ankit.assignmentspringboot.utility.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAuthUserRole {
    @Autowired
    private UserRepository userRepository;

    public UserRole getUserRole() {
        UserModel user = userRepository.findById(GetAuthUserId.getUserId()).orElseThrow(
                () -> new RuntimeException("no user found")
        );
        return user.getRole();
    }
}