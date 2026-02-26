package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.requestDto.LoginRequestDto;
import com.ankit.assignmentspringboot.responseDto.AuthResponseDto;
import com.ankit.assignmentspringboot.service.AuthService;
import com.ankit.assignmentspringboot.service.UserService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> loginUser(@RequestBody LoginRequestDto dto) {
        try {
            System.out.println("trying login with creds -> " + dto.getEmail() + " -> " + dto.getPassword());
            String token = authService.login(dto.getEmail(), dto.getPassword());
            UserModel user = userService.getUserByEmail(dto.getEmail());
            ResponseCookie cookie = ResponseCookie.from("token").value(token)
                    .path("/")
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Strict")
                    .maxAge(3600).build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(
                    new ApiResponse<AuthResponseDto>(
                            true,
                            "user logged in",
                            new AuthResponseDto(user.getRole())
                    )
            );
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ApiResponse<Void>(false, e.getMessage())
            );
        }
    }
}
