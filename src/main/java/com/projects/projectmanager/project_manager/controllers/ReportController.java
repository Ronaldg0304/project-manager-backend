package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.dto.ApiResponse;
import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.dto.TaskDTO;
import com.projects.projectmanager.project_manager.entities.Report;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.impl.ReportService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/report")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<ApiResponse<List<ReportDTO>>> getAllReports() {
//        List<ReportDTO> listReportDTO = service.findAll();
//        return ResponseEntity.ok(new ApiResponse<>("Reports retrieved successfully", listReportDTO, HttpStatus.OK));
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReportDTO>>> getAllReports(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ReportDTO> reportsPage = service.findAll(page, size);
        ApiResponse<Page<ReportDTO>> response = new ApiResponse<>(
                "Reports retrieved successfully",
                reportsPage, HttpStatus.OK
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReportDTO>> create(@Valid @RequestBody ReportDTO reportDTO) {
        ReportDTO savedReportDTO = service.save(reportDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Report created successfully", savedReportDTO, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReportDTO>> detailById(@PathVariable Long id) throws ResourceNotFoundException {
        ReportDTO existingReportDTO = service.findById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Report retrieved successfully", existingReportDTO, HttpStatus.OK));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReportDTO>> update(@PathVariable Long id, @Valid @RequestBody ReportDTO updatedReportDTO) throws ResourceNotFoundException {
        ReportDTO existingReportDTO = service.findById(id).get();
        BeanUtils.copyProperties(updatedReportDTO, existingReportDTO, "id");
        ReportDTO savedReportDTO = service.save(existingReportDTO);
        return ResponseEntity.ok(new ApiResponse<>("Report updated successfully", savedReportDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ReportDTO>> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        ReportDTO deletedReportDTO = service.deleteById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Report deleted successfully", deletedReportDTO, HttpStatus.OK));
    }
}
