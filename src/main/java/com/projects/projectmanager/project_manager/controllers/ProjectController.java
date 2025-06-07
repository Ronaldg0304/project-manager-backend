package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.dto.ApiResponse;
import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.impl.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/project")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getAllProjects() {
        List<ProjectDTO> listProjectDTO = service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Projects retrieved successfully", listProjectDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTO>> create(@Valid @RequestBody ProjectDTO projectDTO) {
        ProjectDTO savedProjectDTO = service.save(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Project created successfully", savedProjectDTO, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> update(@PathVariable Long id, @Valid @RequestBody ProjectDTO updatedProjectDTO) throws ResourceNotFoundException {
        ProjectDTO existingProjectDTO = service.findById(id).get();
        BeanUtils.copyProperties(updatedProjectDTO, existingProjectDTO, "id");
        ProjectDTO savedProjectDTO = service.save(existingProjectDTO);
        return ResponseEntity.ok(new ApiResponse<>("Project updated successfully", savedProjectDTO, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> detailById(@PathVariable Long id) throws ResourceNotFoundException {
        ProjectDTO existingProjectDTO = service.findById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Project retrieved successfully", existingProjectDTO, HttpStatus.OK));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        ProjectDTO deletedProjectDTO = service.deleteById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Project deleted successfully", deletedProjectDTO, HttpStatus.OK));

    }
}
