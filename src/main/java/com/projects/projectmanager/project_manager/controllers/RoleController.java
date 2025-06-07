package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.dto.ApiResponse;
import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.dto.RoleDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.impl.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/role")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllReports() {
        List<RoleDTO> listRoleDTO = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Role retrieved successfully", listRoleDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDTO>> create(@Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO savedRoleDTO = service.save(roleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Role created successfully", savedRoleDTO, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> detailById(@PathVariable Long id) throws ResourceNotFoundException {
        RoleDTO existingRoleDTO = service.findById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Role retrieved successfully", existingRoleDTO, HttpStatus.OK));

    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<ApiResponse<String>> roleByUserId(@PathVariable Long id) {
        String role = service.findRoleIdByUserId(id);
        return ResponseEntity.ok(new ApiResponse<>("Role retrieved successfully", role, HttpStatus.OK));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> update(@PathVariable Long id, @Valid @RequestBody RoleDTO updatedRoleDTO) throws ResourceNotFoundException {
        RoleDTO existingRoleDTO = service.findById(id).get();
        BeanUtils.copyProperties(updatedRoleDTO, existingRoleDTO, "id");
        RoleDTO savedRoleDTO = service.save(existingRoleDTO);
        return ResponseEntity.ok(new ApiResponse<>("Role updated successfully", savedRoleDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        RoleDTO deletedRoleDTO = service.deleteById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Role deleted successfully", deletedRoleDTO, HttpStatus.OK));
    }


}
