package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.requestDto.SaveUserAddressRequestDto;
import com.ankit.assignmentspringboot.requestDto.UpdateUserAddressRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetUserAddressResponseDto;
import com.ankit.assignmentspringboot.service.UserAddressService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import com.ankit.assignmentspringboot.utility.GetAuthUserId;
import com.ankit.assignmentspringboot.utility.UserRole;
import com.ankit.assignmentspringboot.utility.security.GetAuthUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/address")
public class UserAddressController {

    private static final Logger log = LoggerFactory.getLogger(UserAddressController.class);
    private final UserAddressService userAddressService;
    private final GetAuthUserRole getAuthUserRole;

    @Autowired
    public UserAddressController(UserAddressService userAddressService, GetAuthUserRole getAuthUserRole) {
        this.getAuthUserRole = getAuthUserRole;
        log.info("inside user address controller constructor");
        this.userAddressService = userAddressService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveUserAddress(@RequestBody SaveUserAddressRequestDto userAddress){
        try {
            if (
                    !(Objects.equals(userAddress.getUser_id(), GetAuthUserId.getUserId())) &&
                    !(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("operation is not allowed");
            }
            userAddressService.saveUserAddress(userAddress);
            ApiResponse<Void> resp = new ApiResponse<>(true, "saved user address");
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to saved user address", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getUserAddressById(@RequestParam Integer id){
        try {
            if (
                    !(Objects.equals(id.toString(), GetAuthUserId.getUserId())) &&
                            !(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("operation is not allowed");
            }
            GetUserAddressResponseDto existingUserAddress = userAddressService.getUserAddressById(id);
            ApiResponse<GetUserAddressResponseDto> resp = new ApiResponse<>(true, "user address for id: " + id, existingUserAddress);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to get user address", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateUserAddress(@RequestBody UpdateUserAddressRequestDto userAddressRequestDto){
        try {
            if (
                    !(Objects.equals(userAddressRequestDto.getUser_id(), GetAuthUserId.getUserId())) &&
                            !(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("operation is not allowed");
            }
            userAddressService.updateUserAddressData(userAddressRequestDto);
            ApiResponse<Void> resp = new ApiResponse<>(true, "updated user address");
            return ResponseEntity.ok(resp);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to update user address", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteUserAddress(@RequestParam Integer id){
        try {
            if (id == null) throw new NullPointerException();
            if (
                    !(Objects.equals(id.toString(), GetAuthUserId.getUserId())) &&
                            !(List.of(UserRole.ADMIN, UserRole.MODERATOR).contains(getAuthUserRole.getUserRole()))){
                throw new RuntimeException("operation is not allowed");
            }
            userAddressService.deleteUserAddress(id);
            ApiResponse<Void> resp = new ApiResponse<>(true, "user address deleted");
            return ResponseEntity.ok(resp);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to delete user address", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
