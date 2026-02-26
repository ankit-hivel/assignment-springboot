package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.requestDto.SaveUserRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserResponseDto;
import com.ankit.assignmentspringboot.service.UserService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import com.ankit.assignmentspringboot.utility.GetAuthUserId;
import com.ankit.assignmentspringboot.utility.UserRole;
import com.ankit.assignmentspringboot.utility.security.GetAuthUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    private final GetAuthUserRole getAuthUserRole;

    @Autowired
    public UserController(UserService userService, GetAuthUserRole getAuthUserRole) {
        this.getAuthUserRole = getAuthUserRole;
        log.info("inside user controller constructor");
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Void>> saveUser(@RequestBody SaveUserRequestDto user) {
        try {
            userService.saveUserData(user);
            ApiResponse<Void> resp = new ApiResponse<>(true, "saved user");
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch(DataIntegrityViolationException dx) {
            ApiResponse<Void> resp = new ApiResponse<>(false, "user already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to save user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }


    @GetMapping("/details")
    public ResponseEntity<ApiResponse<?>> getUserByAuthenticatedUserId() {
        try{
            GetUserResponseDto user = new GetUserResponseDto(userService.getUserById(GetAuthUserId.getUserId()));
            ApiResponse<GetUserResponseDto> resp = new ApiResponse<>(true, "user found", user);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to get user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<?>> getUserById(@RequestParam String id) {
        try{
            if (id == null) throw new NullPointerException();
            if (!(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("user is not admin or moderator");
            }
            log.info("user role: {}", getAuthUserRole.getUserRole());
            GetUserResponseDto user = new GetUserResponseDto(userService.getUserById(id));
            ApiResponse<GetUserResponseDto> resp = new ApiResponse<>(true, "user found", user);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (RuntimeException rx) {
            log.error("❌{}", String.valueOf(rx));
            ApiResponse<Void> resp = new ApiResponse<>(false, rx.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to get user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }


    @GetMapping(params = "email")
    public ResponseEntity<ApiResponse<?>> getUserByEmail(@RequestParam String email) {
        try {
            if (email == null || email.isEmpty()) throw new NullPointerException();
            if (!(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("user is not admin or moderator");
            }
            GetUserResponseDto user = new GetUserResponseDto(userService.getUserByEmail(email));
            ApiResponse<GetUserResponseDto> resp = new ApiResponse<>(true, "user found", user);
            log.info(String.valueOf(resp.getData()));
            return ResponseEntity.ok(resp);
        } catch (RuntimeException rx) {
            log.error("❌{}", String.valueOf(rx));
            ApiResponse<Void> resp = new ApiResponse<>(false, rx.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to get user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<?>> getAllUsers() {
        try{
            if (!(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("user is not admin or moderator");
            }
            List<UserModel> allUsers = userService.getAllUsers();
            List<GetUserResponseDto> users = allUsers.stream().map(GetUserResponseDto::new).toList();
            ApiResponse<List<GetUserResponseDto>> resp = new ApiResponse<>(true, "all user", users);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException rx) {
            log.error("❌{}", String.valueOf(rx));
            ApiResponse<Void> resp = new ApiResponse<>(false, rx.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to get users");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateUser(@RequestBody SaveUserRequestDto user) {
        try{
            userService.updateExistingUserData(user);
            ApiResponse<Void> resp = new ApiResponse<>(true, "user data updated");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (RuntimeException rx) {
            log.error("❌{}", String.valueOf(rx));
            ApiResponse<Void> resp = new ApiResponse<>(false, rx.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to update user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteUser(@RequestParam String id) {
        try{
            if (id == null) throw new NullPointerException();
            if (!(Objects.equals(UserRole.ADMIN, getAuthUserRole.getUserRole()))) {
                throw new RuntimeException("user should be admin to perform deletion");
            }
            userService.deleteUser(id);
            ApiResponse<Void> resp = new ApiResponse<>(true, "user data deleted");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (RuntimeException rx) {
            log.error("❌{}", String.valueOf(rx));
            ApiResponse<Void> resp = new ApiResponse<>(false, rx.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to delete user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
