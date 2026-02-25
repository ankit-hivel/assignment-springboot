package com.ankit.assignmentspringboot.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.utility.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public String login (String email, String password) {
        UserModel userToMatch = new UserModel();
        userToMatch.setEmail(email);
        UserModel user = userRepository.findOne(
                Example.of(userToMatch, ExampleMatcher.matchingAny()
                        .withMatcher(
                                "email",
                                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()
                        )
                )
        ).orElseThrow(()-> new RuntimeException("user not found"));
        System.out.println("user found: " + user.getPassword());
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        System.out.println("result verified" + result);
        if (result.verified){
            return jwtService.generateToken(user.getId());
        }else{
            throw new RuntimeException("invalid password");
        }
    }
}
