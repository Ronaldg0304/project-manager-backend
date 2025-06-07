package com.projects.projectmanager.project_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Data Transfer Object for Company.
 * Ensures validation and encapsulates company data.
 */
@Data
public class CompanyDTO {

    private Long id;

    @NotNull(message = "Nit company cannot be empty.")
    private Long companyNitId;

    @NotBlank(message = "Company name cannot be empty.")
    @Size(min = 3, max = 100, message = "Company name must be between 3 and 100 characters.")
    private String name;

    @Email(message = "Invalid email format.")
    @Size(max = 80, message = "Email cannot exceed 80 characters.")
    private String email;

    @NotBlank(message = "Phone number cannot be empty.")
    @Size(max = 15, message = "Phone number cannot exceed 15 characters.")
    private String phone;

    @NotBlank(message = "Address cannot be empty.")
    @Size(max = 150, message = "Address cannot exceed 150 characters.")
    private String address;
}

