package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.CompanyAddressModel;
import com.ankit.assignmentspringboot.model.CompanyModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.responseDto.HealthResponseDto;
import com.ankit.assignmentspringboot.responseDto.MetricsResponseDto;
import com.ankit.assignmentspringboot.responseDto.UserExportDataToCsvDto;
import com.ankit.assignmentspringboot.service.RedisService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import com.ankit.assignmentspringboot.utility.CONSTANTS;
import com.ankit.assignmentspringboot.utility.FutureResults;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class MetricsController {
    private static final Logger log = LoggerFactory.getLogger(MetricsController.class);
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final DataSource dataSource;

    public MetricsController(UserRepository userRepository, RedisService redisService, DataSource dataSource) {
        this.userRepository = userRepository;
        this.redisService = redisService;
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<?>> healthCheck() {
        try {
            boolean dbStatus = false;
            boolean redisStatus = false;

            // check db connection
            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<Boolean> dbFuture = executor.submit(() -> {
                try (Connection connection = dataSource.getConnection()) {
                    return connection.isValid(2);
                }
            });

            Future<Boolean> redisFuture = executor.submit(() -> {
                try {
                    String redisValue = redisService.ping();
                    log.info("<{}> received from redis", redisValue);
                    return Objects.equals(redisValue, "PONG");
                } catch (Exception ex) {
                    throw ex;
                }
            });

            dbStatus = FutureResults.getFutureResult(dbFuture, "database");
            redisStatus = FutureResults.getFutureResult(redisFuture, "redis");

            HealthResponseDto healthResponseDto = new HealthResponseDto();
            healthResponseDto.setDb(dbStatus);
            healthResponseDto.setRedis(redisStatus);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(
                            true,
                            "fetched system status",
                            healthResponseDto
                    )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<Void>(false, "system is experiencing issues...")
            );
        }
    }

    @GetMapping(value = "/csv")
    public ResponseEntity<ApiResponse<?>> exportUsersToCsv() {
        try {
            // get all users data
            List<UserModel> users = userRepository.findAll();

            String CSV_FILE_PATH = "./static/data.csv";
            Path pathToCsv = Path.of(CSV_FILE_PATH);
            log.info("deleting csv file if exists");
            Files.deleteIfExists(pathToCsv);
            log.info("existing file deleted, creating new csv file");
            Files.createFile(pathToCsv);
            log.info("csv file created, writing data into file");

            Writer writer = new FileWriter(CSV_FILE_PATH);
            StatefulBeanToCsv<UserExportDataToCsvDto> beanToCsv = new StatefulBeanToCsvBuilder<UserExportDataToCsvDto>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            List<UserExportDataToCsvDto> allUsersData = users.stream().map(
                    (user) -> {

                        CompanyModel company = user.getCompany();
                        CompanyAddressModel companyAddress = null;

                        if (company != null) {
                            companyAddress = company.getCompanyAddress();
                        }

                        return new UserExportDataToCsvDto(
                                user,
                                user.getUserAddress(),
                                company,
                                companyAddress
                        );
                    }
            ).toList();
            beanToCsv.write(allUsersData);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(true, "data exported to file: " + pathToCsv)
            );
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(false, "failed to export data")
            );
        }
    }

    @GetMapping("/metrics")
    public ResponseEntity<ApiResponse<?>> getMetrics() {
        try {
            String cacheMissValue = redisService.get(CONSTANTS.CacheMissCountKey);
            String cacheHitValue = redisService.get(CONSTANTS.CacheHitCountKey);
            MetricsResponseDto metrics = new MetricsResponseDto();
            metrics.setCacheHit(cacheHitValue);
            metrics.setCacheMiss(cacheMissValue);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>(true, "system metrics",
                            metrics
                    )
            );
        } catch (Exception e) {
            log.error("e: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(false, "failed to get metrics")
            );
        }
    }
}