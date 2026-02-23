package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.service.UserService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            System.out.println("PAYLOAD: " + user);
            userService.saveUserData(user);
            ApiResponse resp = new ApiResponse(true, "saved user", null);
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to save user", null);
            return ResponseEntity.ok(resp);
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse> getUserByIdOrEmail(@RequestParam Integer id) {
        Optional<UserModel> user = userService.getUserById(id);
        ApiResponse resp = new ApiResponse(true, "user found", user);
        return ResponseEntity.ok(resp);
    }


    @GetMapping(params = "email")
    public ResponseEntity<ApiResponse> getUserByIdOrEmail(@RequestParam String email) {
        Optional<UserModel> user = userService.getUserByEmail(email);
        ApiResponse resp = new ApiResponse(true, "user found", user);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserModel> allUsers = userService.getAllUsers();
        ApiResponse resp = new ApiResponse(true, "all user", allUsers);
        return ResponseEntity.ok(resp);
    }
}
