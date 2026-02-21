package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserModel user) {
        System.out.println(user);
        userService.saveUserData(user);
        userService.getUserById(1);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
