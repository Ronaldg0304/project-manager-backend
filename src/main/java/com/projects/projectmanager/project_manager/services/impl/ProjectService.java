package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;


import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectDTO> findAll();
    Optional<ProjectDTO> findById(Long id) throws ResourceNotFoundException;
    ProjectDTO save(ProjectDTO projectDTO);
    Optional<ProjectDTO> deleteById(Long id) throws ResourceNotFoundException;
}
