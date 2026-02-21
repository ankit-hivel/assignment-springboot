package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveUser(@RequestBody UserModel user) {
        System.out.println(user);
        userService.saveUserData(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HttpStatus> getUserById(@RequestParam int id) {
        userService.getUserById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<HttpStatus> getUserByEmail(@RequestParam String email){
        System.out.println("email: " + email);
        userService.getUserByEmail(email);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
