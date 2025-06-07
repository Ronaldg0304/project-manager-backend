package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.TaskDTO;
import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {

    List<UserEntityDTO> findAll();
    Optional<UserEntityDTO> findById(Long id) throws ResourceNotFoundException;
    UserEntityDTO save(UserEntityDTO userEntityDTO);
    Optional<UserEntityDTO> deleteById(Long id) throws ResourceNotFoundException;
    Optional<UserEntityDTO> findByUserName (String userName) throws UsernameNotFoundException;
    Optional<UserEntityDTO> findByDocumentId (Long id) throws ResourceNotFoundException;
}
