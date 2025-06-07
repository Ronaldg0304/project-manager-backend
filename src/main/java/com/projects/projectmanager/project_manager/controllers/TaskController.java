package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.dto.*;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.impl.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTasks() {
        List<TaskDTO> listTaskDTO= service.findAll();
        return ResponseEntity.ok(new ApiResponse<>("Tasks retrieved successfully", listTaskDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskDTO>> create(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO savedTaskDTO = service.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Task created successfully", savedTaskDTO, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> detailById(@PathVariable Long id) throws ResourceNotFoundException {
        TaskDTO existingTaskDTO = service.findById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Task retrieved successfully", existingTaskDTO, HttpStatus.OK));

    }

    @GetMapping("projectId/{id}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTaskByProjectId(@PathVariable Long id){
        List<TaskDTO> listTaskDTO = service.findAllByProjectId(id);
        return ResponseEntity.ok(new ApiResponse<>("Task retrieved successfully", listTaskDTO, HttpStatus.OK));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> update(@PathVariable Long id, @Valid @RequestBody TaskDTO updatedTaskDTO) throws ResourceNotFoundException {
        TaskDTO existingTaskDTO = service.findById(id).get();
        BeanUtils.copyProperties(updatedTaskDTO, existingTaskDTO, "id");
        TaskDTO savedTaskDTO = service.save(existingTaskDTO);
        return ResponseEntity.ok(new ApiResponse<>("Task updated successfully", savedTaskDTO, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        TaskDTO deletedTaskDTO = service.deleteById(id).get();
        return ResponseEntity.ok(new ApiResponse<>("Task deleted successfully", deletedTaskDTO, HttpStatus.OK));
    }
}
