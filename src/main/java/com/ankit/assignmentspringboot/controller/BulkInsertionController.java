package com.ankit.assignmentspringboot.controller;

import com.ankit.assignmentspringboot.requestDto.BulkDeletionRequestDto;
import com.ankit.assignmentspringboot.requestDto.BulkInsertRequestDto;
import com.ankit.assignmentspringboot.service.BulkInsertService;
import com.ankit.assignmentspringboot.utility.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulk")
public class BulkInsertionController {
    private static final Logger log = LoggerFactory.getLogger(BulkInsertionController.class);
    private final BulkInsertService bulkInsertService;

    public BulkInsertionController(BulkInsertService bulkInsertService) {
        this.bulkInsertService = bulkInsertService;
    }


    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<Void>> bulkInsertion(@RequestBody List<BulkInsertRequestDto> data) {
        try {
            bulkInsertService.insertData(data);
            ApiResponse<Void> resp = new ApiResponse<>(true, "bulk insertion complete");
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (DataIntegrityViolationException dx) {
            log.error("❌{}", String.valueOf(dx));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to save data due to duplicate values");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> bulkDeletion(@RequestBody BulkDeletionRequestDto data) {
        try {
            bulkInsertService.deleteUsersByIds(data.getIds());
            ApiResponse<Void> resp = new ApiResponse<>(true, "bulk deletion complete");
            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
            log.error("❌{}", String.valueOf(e));
            ApiResponse<Void> resp = new ApiResponse<>(false, "failed to perform bulk deletion");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
