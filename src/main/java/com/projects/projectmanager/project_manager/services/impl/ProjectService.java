package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface ProjectService {

    //List<ProjectDTO> findAll();
    Optional<ProjectDTO> findById(Long id) throws ResourceNotFoundException;
    ProjectDTO save(ProjectDTO projectDTO);
    Optional<ProjectDTO> deleteById(Long id) throws ResourceNotFoundException;
    Page<ProjectDTO> findAll(int page, int size);
}
