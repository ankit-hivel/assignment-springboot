package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUserData(UserModel user){
        userRepository.save(user);
    }

    public Optional<UserModel> getUserById(int id) {
        Optional<UserModel> user = userRepository.findById(id);
        System.out.println("got user: " + user);
        return user;
    }

    public Optional<UserModel> getUserByEmail(String email){
        UserModel user = new UserModel();
        user.setEmail(email);
        Optional<UserModel> existingUser = userRepository.findOne(
                Example.of(user, ExampleMatcher.matchingAny()
                        .withMatcher(
                                "email",
                                ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()
                        )
                )
        );
        System.out.println("existing user: " + existingUser);
        return existingUser;
    }
}
