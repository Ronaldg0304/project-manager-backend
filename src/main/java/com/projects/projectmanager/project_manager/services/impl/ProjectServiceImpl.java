package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.mappers.ProjectMapper;
import com.projects.projectmanager.project_manager.repositories.CompanyRepository;
import com.projects.projectmanager.project_manager.repositories.ProjectRepository;
import com.projects.projectmanager.project_manager.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;
    private final CompanyRepository companyRepository;
    private final UserEntityRepository userEntityRepository;

    public ProjectServiceImpl(ProjectRepository repository, ProjectMapper mapper, CompanyRepository companyRepository, UserEntityRepository userEntityRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.companyRepository = companyRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectDTO> findAll() {
        List<Project> projectList = (List<Project>) repository.findAll();
        return projectList.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ProjectDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Project> optionalProject = repository.findById(id);
        if (!optionalProject.isPresent()) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        return optionalProject.map(mapper::toDTO);
    }

    @Transactional
    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        Company company = companyRepository.findById(projectDTO.getCompanyId()).get();
        UserEntity userEntity = userEntityRepository.findById(projectDTO.getUserId()).get();
        Project project = mapper.toEntity(projectDTO, company, userEntity);
        return mapper.toDTO(repository.save(project));
    }

    @Transactional
    @Override
    public Optional<ProjectDTO> deleteById(Long id) throws ResourceNotFoundException {
        Optional<Project> optionalProject = repository.findById(id);
        if (!optionalProject.isPresent()) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        repository.deleteById(id);
        return optionalProject.map(mapper::toDTO);
    }
}
