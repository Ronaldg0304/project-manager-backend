package com.projects.projectmanager.project_manager.mappers;

import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import com.projects.projectmanager.project_manager.entities.Role;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.repositories.RoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper {

    private final RoleRepository roleRepository;

    public UserEntityMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserEntityDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserEntityDTO dto = new UserEntityDTO();
        dto.setId(userEntity.getId());
        dto.setDocumentId(userEntity.getDocumentId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setSecondName(userEntity.getSecondName());
        dto.setFirstLastName(userEntity.getFirstLastName());
        dto.setSecondLastName(userEntity.getSecondLastName());
        dto.setEmail(userEntity.getEmail());
        dto.setUsername(userEntity.getUsername());
        dto.setPassword(userEntity.getPassword());

        if (userEntity.getCompany() != null) {
            dto.setCompanyId(userEntity.getCompany().getId());
        }

        if (userEntity.getRoles() != null && !userEntity.getRoles().isEmpty()) {
            dto.setRoleId(userEntity.getRoles().stream()
                    .findFirst()
                    .map(role -> role.getId())
                    .orElse(null));
        }

        return dto;
    }

    public UserEntity toEntity(UserEntityDTO dto) {
        if (dto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setDocumentId(dto.getDocumentId());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setSecondName(dto.getSecondName());
        userEntity.setFirstLastName(dto.getFirstLastName());
        userEntity.setSecondLastName(dto.getSecondLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());

        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            userEntity.setCompany(company);
        }

        if (dto.getRoleId() != null) {
            try {
                Role role = roleRepository.findById(dto.getRoleId())
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado con ID: " + dto.getRoleId()));
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                userEntity.setRoles(roles);
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        return userEntity;
    }
}


