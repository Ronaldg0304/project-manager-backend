package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    List<ReportDTO> findAll();
    Optional<ReportDTO> findById(Long id) throws ResourceNotFoundException;
    ReportDTO save(ReportDTO reportDTO);
    Optional<ReportDTO> deleteById(Long id) throws ResourceNotFoundException;
}
