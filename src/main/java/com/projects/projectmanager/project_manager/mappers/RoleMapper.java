package com.projects.projectmanager.project_manager.mappers;

import com.projects.projectmanager.project_manager.entities.Role;
import com.projects.projectmanager.project_manager.dto.RoleDTO;
import com.projects.projectmanager.project_manager.entities.enums.ERole;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName().name()); // Aquí convertimos el enum a su nombre como String
        return dto;
    }

    public Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(ERole.valueOf(roleDTO.getName())); // Convertimos el String al enum correspondiente
        return role;
    }
}
