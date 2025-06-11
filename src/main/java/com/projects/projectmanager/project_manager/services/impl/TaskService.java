package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.dto.TaskDTO;
import com.projects.projectmanager.project_manager.entities.Task;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskService{

    //List<TaskDTO> findAll();
    Optional<TaskDTO> findById(Long id) throws ResourceNotFoundException;
    List<TaskDTO> findAllByProjectId(Long projectId);
    TaskDTO save(TaskDTO taskDTO);
    Optional<TaskDTO> deleteById(Long id) throws ResourceNotFoundException;
    Page<TaskDTO> findAll(int page, int size);
}
