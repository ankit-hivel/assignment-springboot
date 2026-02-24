package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.requestDto.SaveCompanyRequestDto;
import com.ankit.assignmentspringboot.requestDto.UpdateCompanyRequestDto;
import com.ankit.assignmentspringboot.responseDto.GetCompanyResponseDto;
import com.ankit.assignmentspringboot.service.CompanyService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        System.out.println("inside company controller constructor");
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveCompany(@RequestBody SaveCompanyRequestDto dto){
        System.out.println(dto.getAddress());
        companyService.saveCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<Void>(true, "company saved")
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<GetCompanyResponseDto>> getCompanyById(@RequestParam int id) {
        GetCompanyResponseDto company = companyService.getCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<GetCompanyResponseDto>(true, "company found", company)
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateCompanyById(@RequestBody UpdateCompanyRequestDto dto) {
        companyService.updateCompanyById(dto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<Void>(true, "company updated")
        );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteCompanyById(@RequestParam Integer id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<Void>(true, "company deleted successfully")
        );
    }
}
