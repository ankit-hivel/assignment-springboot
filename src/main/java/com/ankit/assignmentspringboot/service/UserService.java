package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void getUserById(int id) {
        Optional<UserModel> user = userRepository.findById(id);
        System.out.println("got user: " + user);
    }
}
