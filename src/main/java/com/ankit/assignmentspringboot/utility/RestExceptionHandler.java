package com.ankit.assignmentspringboot.utility;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception e) {
        System.out.println("Got Exception ❌❌❌:" + e);
        ApiResponse resp = new ApiResponse();
        resp.setStatus(false);
        resp.setMessage("Internal Server Error");
        return ResponseEntity.ok(resp);
    }
}
