package com.projects.projectmanager.project_manager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for Task.
 * Ensures validation and encapsulates Task data.
 */

@Data
public class TaskDTO {

    private Long id;

    @NotBlank(message = "Task name cannot empty")
    @Size(min = 3, max = 100, message = "Task name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 250, message = "Description cannot exceed 250 characters")
    private String description;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @FutureOrPresent(message = "End date must be today or in the future")
    private LocalDate endDate;

    @NotNull(message = "Estimated budget cannot be empty")
    @Positive(message = "Estimated budget must be positive")
    private Double estimatedBudget;

    @NotNull(message = "Actual budget cannot be empty")
    @Positive(message = "Actual budget must be positive")
    private Double actualBudget;

    @NotBlank
    private String stateTask;

    @NotBlank
    private String priority;

    private Long projectId;

    private Long userId;
}
