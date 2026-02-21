package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.service.UserService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController extends Exception{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserModel user) {
        try {
            System.out.println(user);
            userService.saveUserData(user);
            ApiResponse resp = new ApiResponse(true, "saved user", null);
            return ResponseEntity.ok(resp);
        }catch (Exception e){
            ApiResponse resp = new ApiResponse(false, "failed to save user", null);
            return ResponseEntity.ok(resp);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getUserByIdOrEmail(@RequestParam int id, @RequestParam String email) {
        Optional<UserModel> user = (email != null) ? userService.getUserByEmail(email): userService.getUserById(id);
        ApiResponse resp = new ApiResponse(true, "user found", user);
        return ResponseEntity.ok(resp);
    }
}
