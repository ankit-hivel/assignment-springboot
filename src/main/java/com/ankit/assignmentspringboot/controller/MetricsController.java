package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.model.CompanyAddressModel;
import com.ankit.assignmentspringboot.model.CompanyModel;
import com.ankit.assignmentspringboot.model.UserModel;
import com.ankit.assignmentspringboot.repository.UserRepository;
import com.ankit.assignmentspringboot.responseDto.UserExportDataToCsvDto;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MetricsController {
    private final String CSV_FILE_PATH = "data.csv";
    private final UserRepository userRepository;

    public MetricsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> healthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<Void>(true, "system is running...")
        );
    }

    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<ApiResponse<?>> exportUsersToCsv() {
        try {
            // get all users data
            List<UserModel> users = userRepository.findAll();

            Path path = Path.of(CSV_FILE_PATH);
            Files.deleteIfExists(path);
            Files.createFile(path);

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
                    new ApiResponse<>(true, "data exported")
            );
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(false, "failed to export data")
            );
        }
    }
}