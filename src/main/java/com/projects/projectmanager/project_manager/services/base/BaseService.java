package com.projects.projectmanager.project_manager.services.base;

import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;

import java.util.Optional;

public interface BaseService <T, ID> {
        Optional<T> findById(ID id) throws ResourceNotFoundException;
}
