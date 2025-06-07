package com.projects.projectmanager.project_manager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for Project.
 * Ensures validation and encapsulates project data.
 */
@Data
public class ProjectDTO {

    private Long id;

    @NotBlank(message = "Project name cannot be empty")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters")
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

    @NotNull(message = "Estimated budget cannot be null")
    @Min(value = 0, message = "Estimated budget must be at least 0")
    private BigDecimal estimatedBudget;

    @NotNull(message = "Actual budget cannot be null")
    @Min(value = 0, message = "Actual budget must be at least 0")
    private BigDecimal actualBudget;

    @Min(value = 0, message = "Tax must be at least 0")
    private BigDecimal taxPercentage;

    @Min(value = 0, message = "Additional costs must be at least 0")
    private BigDecimal additionalCosts;

    private BigDecimal totalCost;

    @NotBlank
    private String stateProject;

    private Long companyId;

    private Long userId;
}
