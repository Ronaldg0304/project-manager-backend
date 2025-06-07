package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.dto.RoleDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<RoleDTO> findAll();
    Optional<RoleDTO> findById(Long id) throws ResourceNotFoundException;
    RoleDTO save(RoleDTO roleDTO);
    Optional<RoleDTO> deleteById(Long id) throws ResourceNotFoundException;
    String findRoleIdByUserId (Long id);
}
