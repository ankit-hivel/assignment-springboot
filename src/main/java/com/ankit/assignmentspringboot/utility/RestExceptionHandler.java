package com.ankit.assignmentspringboot.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(RestExceptionHandler.class);

    // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NoHandlerFoundException ex) {

        log.warn("Route not found: {}", ex.getRequestURL());

        ApiResponse<Void> resp = new ApiResponse<>();
        resp.setStatus(false);
        resp.setMessage("Route not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception e) {

        log.error("Unhandled Exception ❌", e);

        ApiResponse<Void> resp = new ApiResponse<>();
        resp.setStatus(false);
        resp.setMessage("Internal Server Error");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }
}