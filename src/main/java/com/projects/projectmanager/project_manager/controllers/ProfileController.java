package com.projects.projectmanager.project_manager.controllers;

import com.projects.projectmanager.project_manager.dto.ProfileDTO;
import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.impl.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app/profile")
public class ProfileController {

    private final UserEntityService service;

    public ProfileController(UserEntityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfile(Authentication authentication) throws ResourceNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserEntityDTO userProfile = service.findByUserName(userDetails.getUsername()).get();


        String username = userDetails.getUsername();
        Long userId = userProfile.getId();
        String firstName = userProfile.getFirstName();
        String secondName = userProfile.getSecondName();
        String firstLastName = userProfile.getFirstLastName();
        String secondLastName = userProfile.getSecondLastName();
        String email = userProfile.getEmail();
        Long companyId = userProfile.getCompanyId();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_INVITED");

        ProfileDTO profile = new ProfileDTO(userId, username, firstName, secondName, firstLastName, secondLastName, companyId, email, role);
        return ResponseEntity.ok(profile);
    }
}
