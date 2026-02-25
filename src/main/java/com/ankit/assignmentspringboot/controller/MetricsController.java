package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<Void>(true, "system is running...")
        );
    }
}