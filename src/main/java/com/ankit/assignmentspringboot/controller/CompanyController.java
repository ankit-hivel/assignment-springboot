package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.requestDto.SaveCompanyRequestDto;
import com.ankit.assignmentspringboot.requestDto.UpdateCompanyRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetCompanyResponseDto;
import com.ankit.assignmentspringboot.service.CompanyService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        log.info("inside company controller constructor");
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveCompany(@RequestBody SaveCompanyRequestDto dto){
        try {
            companyService.saveCompany(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ApiResponse<Void>(true, "company saved")
            );
        } catch(Exception ex) {
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to save company details", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<?>> getCompanyById(@RequestParam Integer id) {
        try {
            if (id == null) throw new NullPointerException();
            log.info("getting company data...");
            GetCompanyResponseDto company = companyService.getCompanyById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<GetCompanyResponseDto>(true, "company found", company)
            );
        } catch(Exception ex) {
            log.error("failed to get data -> {}", ex.getMessage());
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to get company details", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateCompanyById(@RequestBody UpdateCompanyRequestDto dto) {
        try {
            companyService.updateCompanyById(dto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<Void>(true, "company updated")
            );
        } catch(Exception ex) {
            log.error(ex.getMessage());
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to update company details", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteCompanyById(@RequestParam Integer id) {
        try{
            if (id == null) throw new NullPointerException();
            companyService.deleteCompanyById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<Void>(true, "company deleted successfully")
            );
        } catch(Exception ex) {
            log.error("failed to delete company details: ", ex);
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to delete company details", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
