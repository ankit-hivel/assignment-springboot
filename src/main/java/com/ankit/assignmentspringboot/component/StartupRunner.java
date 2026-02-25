package com.ankit.assignmentspringboot.component;

import com.ankit.assignmentspringboot.service.RestApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    private final RestApiService restApiService;

    public StartupRunner(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @Override
    public void run(String... args) {
//        restApiService.getAllUsers();
    }
}
