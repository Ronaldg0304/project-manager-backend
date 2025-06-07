package com.projects.projectmanager.project_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProfileDTO {
    private  Long userId;
    private String username;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private Long companyId;
    private String email;
    private String role;
}
