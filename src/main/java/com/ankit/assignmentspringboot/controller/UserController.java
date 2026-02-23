package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserResponseDto;
import com.ankit.assignmentspringboot.service.UserService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody SaveUserRequestDto user) {
        try {
            userService.saveUserData(user);
            ApiResponse resp = new ApiResponse(true, "saved user");
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch(DataIntegrityViolationException dx) {
            ApiResponse resp = new ApiResponse(false, "user already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to save user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse> getUserByIdOrEmail(@RequestParam Integer id) {
        try{
            UserModel user = userService.getUserById(id);
            ApiResponse resp = new ApiResponse(true, "user found", new GetUserResponseDto(user));
            return ResponseEntity.ok(resp);
        }catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to save user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }


    @GetMapping(params = "email")
    public ResponseEntity<ApiResponse> getUserByIdOrEmail(@RequestParam String email) {
        try {
            UserModel user = userService.getUserByEmail(email);
            ApiResponse resp = new ApiResponse(true, "user found", new GetUserResponseDto(user));
            System.out.println(resp.getData());
            return ResponseEntity.ok(resp);
        }catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to save user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try{
            List<UserModel> allUsers = userService.getAllUsers();
            List<GetUserResponseDto> users = allUsers.stream().map(GetUserResponseDto::new).toList();
            ApiResponse resp = new ApiResponse(true, "all user", users);
            return ResponseEntity.ok(resp);
        }catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to save user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateUser(@RequestBody SaveUserRequestDto user) {
        try{
            userService.updateExistingUserData(user);
            ApiResponse resp = new ApiResponse(true, "user data updated");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to update user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam int id) {
        try{
            userService.deleteUser(id);
            ApiResponse resp = new ApiResponse(true, "user data deleted");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e) {
            System.out.println("❌" + e);
            ApiResponse resp = new ApiResponse(false, "failed to delete user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
