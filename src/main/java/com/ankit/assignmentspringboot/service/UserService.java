package com.ankit.assignmentspringboot.service;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserAddressRepository;
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
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserAddressRepository userAddressRepository){
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
    }

    public void saveUserData(UserModel user){
        userAddressRepository.save(user.getAddress());
        userRepository.save(user);
    }

    public Optional<UserModel> getUserById(Integer id) {
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

    public List<UserModel> getAllUsers() {
        System.out.println("users: ->>>" + userRepository.findAllUsers());
        return List.of();
    }
}
