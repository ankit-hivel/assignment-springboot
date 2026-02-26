package com.ankit.assignmentspringboot.component;

import com.ankit.assignmentspringboot.service.RedisService;
import com.ankit.assignmentspringboot.service.RestApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);
    private final RestApiService restApiService;
    private final RedisService redisService;

    public StartupRunner(RestApiService restApiService, RedisService redisService) {
        this.restApiService = restApiService;
        this.redisService = redisService;
    }

    @Override
    public void run(String... args) {
//        restApiService.getAllUsers();
        try {
            log.info("ping to redis");
            String redisConn = redisService.ping();
            log.info("<{}> received from redis", redisConn);
            log.info("CONNECTED TO REDIS...");
        } catch (RedisConnectionFailureException rx) {
            log.error("redis connection failed, check configuration");
            log.error(rx.getMessage());
            throw rx;
        }
    }
}
