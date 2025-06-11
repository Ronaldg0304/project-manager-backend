package com.projects.projectmanager.project_manager.controllers;


import com.projects.projectmanager.project_manager.dto.ApiResponse;
import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.security.UserDetailsServiceImpl;
import com.projects.projectmanager.project_manager.services.impl.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
public class UserEntityController {

    private final UserEntityService service;
    private final UserDetailsServiceImpl userService;

    public UserEntityController(UserEntityService service, UserDetailsServiceImpl userService) {
        this.service = service;
        this.userService = userService;
    }

//    @GetMapping
//    public ResponseEntity<ApiResponse<List<UserEntityDTO>>> getAllUserEntity() {
//        List<UserEntityDTO> listUserEntity = service.findAll();
//        return ResponseEntity.ok(new ApiResponse<>("Users retrieved successfully", listUserEntity, HttpStatus.OK));
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserEntityDTO>>> getAllUserEntity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserEntityDTO> usersPage = service.findAll(page, size);
        ApiResponse<Page<UserEntityDTO>> response = new ApiResponse<>(
                "Users retrieved successfully",
                usersPage, HttpStatus.OK
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserEntityDTO>> create(@Valid @RequestBody UserEntityDTO userEntityDTO) {
        UserEntityDTO savedUserEntityDTO = service.save(userEntityDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("User created successfully", savedUserEntityDTO, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserEntityDTO>> detailById(@PathVariable Long id) throws ResourceNotFoundException {
        UserEntityDTO existingUserEntityDTO = service.findById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("User retrieved successfully", existingUserEntityDTO, HttpStatus.OK));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserEntityDTO>> update(@PathVariable Long id, @Valid @RequestBody UserEntityDTO updatedUserEntityDTO) throws ResourceNotFoundException {
        UserEntityDTO existingUserEntityDTO = service.findById(id).get();
        BeanUtils.copyProperties(updatedUserEntityDTO, existingUserEntityDTO, "id");
        UserEntityDTO savedUserEntityDTO = service.save(existingUserEntityDTO);
        return ResponseEntity.ok(new ApiResponse<>("User updated successfully", savedUserEntityDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserEntityDTO>> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        UserEntityDTO deletedUserEntityDTO = service.deleteById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("User deleted successfully", deletedUserEntityDTO, HttpStatus.OK));

    }

    @GetMapping("/documentId/{id}")
    public ResponseEntity<ApiResponse<UserEntityDTO>> detailByDocumentId(@PathVariable Long id) throws ResourceNotFoundException {
        UserEntityDTO existingUserEntityDTO = service.findByDocumentId(id).get();
        return ResponseEntity.ok(new ApiResponse<>("User retrieved successfully", existingUserEntityDTO, HttpStatus.OK));

    }
//    @GetMapping("/documenteId/{id}")
//    public ResponseEntity<ApiResponse<UserEntityDTO>> detailByUsername(@PathVariable String username) throws ResourceNotFoundException {
//        UserEntityDTO existingUserEntity = service.findByUserName(username).get();
//        return ResponseEntity.ok(new ApiResponse<>("User retrieved successfully", existingUserEntity, HttpStatus.OK));
//
//    }

}