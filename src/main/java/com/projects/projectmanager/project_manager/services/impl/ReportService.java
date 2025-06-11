package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    //List<ReportDTO> findAll();
    Optional<ReportDTO> findById(Long id) throws ResourceNotFoundException;
    ReportDTO save(ReportDTO reportDTO);
    Optional<ReportDTO> deleteById(Long id) throws ResourceNotFoundException;
    Page<ReportDTO> findAll(int page, int size);
}
