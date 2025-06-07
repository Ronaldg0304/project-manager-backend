package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.services.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<CompanyDTO> findAll();
    Optional<CompanyDTO> findById(Long id) throws ResourceNotFoundException;
    Optional<CompanyDTO> findByCompanyNitId(Long companyNitId) throws ResourceNotFoundException;
    CompanyDTO save(CompanyDTO companyDTO);
    Optional<CompanyDTO> deleteById(Long id) throws ResourceNotFoundException;
}
