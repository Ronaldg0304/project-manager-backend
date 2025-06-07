package com.projects.projectmanager.project_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

/**
 * Data Transfer Object for UserEntity.
 * Ensures validation and encapsulates UserEntity data.
 */
@Data
public class UserEntityDTO {

    private Long id;

    @NotBlank(message = "Document ID cannot be empty")
    private String documentId;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    private String secondName;

    @NotBlank(message = "First name cannot be empty")
    private String firstLastName;

    @NotBlank(message = "Last name cannot be empty")
    private String secondLastName;

    @Email(message = "Invalid email format.")
    @Size(max = 80, message = "Email cannot exceed 80 characters.")
    private String email;

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters.")
    private String username;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 3, max = 100, message = "Password must be between 3 and 100 characters.")
    private String password;

    private Long companyId;

    private Long roleId;

}
