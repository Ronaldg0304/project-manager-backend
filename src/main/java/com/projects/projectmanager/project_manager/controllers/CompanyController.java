package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.dto.ApiResponse;
import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.impl.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/app/company")
@CrossOrigin(origins = "http://localhost:5173")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyDTO>>> getAllCompanies() {
        List<CompanyDTO> listCompanyDTO = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Companies retrieved successfully", listCompanyDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyDTO>> create(@Valid @RequestBody CompanyDTO companyDTO) {
        CompanyDTO savedCompanyDTO = service.save(companyDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Company created successfully", savedCompanyDTO, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyDTO>> update(@Valid @RequestBody CompanyDTO updatedCompanyDTO, @PathVariable Long id) throws ResourceNotFoundException {
        CompanyDTO existingCompany = service.findById(id).get();
        BeanUtils.copyProperties(updatedCompanyDTO, existingCompany, "id");
        CompanyDTO savedCompanyDTO= service.save(existingCompany);
        return ResponseEntity.ok(new ApiResponse<>("Company details updated successfully", savedCompanyDTO, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyDTO>> detailById(@PathVariable Long id) throws ResourceNotFoundException {
        CompanyDTO existingCompanyDTO = service.findById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Company retrieved successfully", existingCompanyDTO, HttpStatus.OK));
    }
    @GetMapping("/nit/{companyNitId}")
    public ResponseEntity<ApiResponse<CompanyDTO>> detailByNit(@PathVariable Long companyNitId) throws ResourceNotFoundException {
        CompanyDTO existingCompanyDTO = service.findByCompanyNitId(companyNitId).get();
        return ResponseEntity.ok(new ApiResponse<>("Company retrieved successfully", existingCompanyDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyDTO>> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        CompanyDTO deletedCompanyDTO = service.deleteById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Company deleted successfully", deletedCompanyDTO, HttpStatus.OK));
    }

//    @DeleteMapping("nit/{companyNitId}")
//    public ResponseEntity<ApiResponse<CompanyDTO>> deleteByNit(@PathVariable Long companyNitId)  throws ResourceNotFoundException {
//        CompanyDTO companyDTO = service.findByCompanyNitId(companyNitId).get();
//        service.deleteById(companyNitId);
//        return ResponseEntity.ok(new ApiResponse<>("Company retrieved successfully", companyDTO, HttpStatus.OK));
//    }
}
