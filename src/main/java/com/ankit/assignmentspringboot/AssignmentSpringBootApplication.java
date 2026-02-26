package com.ankit.assignmentspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AssignmentSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentSpringBootApplication.class, args);

    }
}
