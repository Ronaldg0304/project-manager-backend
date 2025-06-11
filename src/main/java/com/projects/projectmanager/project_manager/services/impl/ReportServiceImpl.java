package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.Report;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.mappers.ReportMapper;
import com.projects.projectmanager.project_manager.repositories.ProjectRepository;
import com.projects.projectmanager.project_manager.repositories.ReportRepository;
import com.projects.projectmanager.project_manager.repositories.UserEntityRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService{

    private final ReportRepository repository;
    private final ReportMapper mapper;
    private final ProjectRepository projectRepository;
    private final UserEntityRepository userEntityRepository;

    public ReportServiceImpl(ReportRepository repository, ReportMapper mapper, ProjectRepository projectRepository, UserEntityRepository userEntityRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.userEntityRepository = userEntityRepository;
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<ReportDTO> findAll() {
//        List<Report> reportsList = (List<Report>) repository.findAll();
//        return reportsList.stream()
//                .map(mapper::toDTO)
//                .toList();
//    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ReportDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Report> optionalReport = repository.findById(id);
        if(!optionalReport.isPresent()){
            throw new ResourceNotFoundException("Report not found with id: " + id);
        }
        return optionalReport.map(mapper::toDTO);
    }

    @Transactional
    @Override
    public ReportDTO save(ReportDTO reportDTO) {
        Project project = projectRepository.findById(reportDTO.getProjectId()).get();
        UserEntity userEntity = userEntityRepository.findById(reportDTO.getUserEntityId()).get();
        Report report = mapper.toEntity(reportDTO, userEntity, project);
        report.setProject(project);
        report.setUser(userEntity);

        return mapper.toDTO(repository.save(report));
    }

    @Transactional
    @Override
    public Optional<ReportDTO> deleteById(Long id) throws ResourceNotFoundException {
        Optional<Report> optionalReport = repository.findById(id);
        if(!optionalReport.isPresent()){
            throw new ResourceNotFoundException("Report not found with id: " + id);
        }
        repository.deleteById(id);
        return optionalReport.map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ReportDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("reportDate").descending());

        Page<Report> reports = repository.findAll(pageable);

        List<ReportDTO> content = reports.getContent().stream()
                .map(mapper::toDTO)
                .toList();

        return new PageImpl<>(content, pageable, reports.getTotalElements());
    }
}
