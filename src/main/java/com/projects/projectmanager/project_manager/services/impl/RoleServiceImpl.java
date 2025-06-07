package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.RoleDTO;
import com.projects.projectmanager.project_manager.entities.Role;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.mappers.ReportMapper;
import com.projects.projectmanager.project_manager.mappers.RoleMapper;
import com.projects.projectmanager.project_manager.repositories.ProjectRepository;
import com.projects.projectmanager.project_manager.repositories.ReportRepository;
import com.projects.projectmanager.project_manager.repositories.RoleRepository;
import com.projects.projectmanager.project_manager.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;
    private final RoleMapper mapper;
    private final UserEntityRepository userEntityRepository;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper, UserEntityRepository userEntityRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userEntityRepository = userEntityRepository;
    }


    @Override
    public List<RoleDTO> findAll() {
        List<Role> roleList =(List<Role>) repository.findAll();
        return roleList.stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<RoleDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Role> optionalRole = repository.findById(id);
        if(!optionalRole.isPresent()){
            throw new ResourceNotFoundException("Report not found with id: "+ id);
        }
        return optionalRole.map(mapper::toDTO);
    }

    @Transactional
    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = mapper.toEntity(roleDTO);
        return mapper.toDTO(repository.save(role));
    }

    @Transactional
    @Override
    public Optional<RoleDTO> deleteById(Long id) throws ResourceNotFoundException {
        Optional<Role> optionalRole = repository.findById(id);
        if(!optionalRole.isPresent()){
            throw new ResourceNotFoundException("Role not found with id: "+ id);
        }
        repository.deleteById(id);
        return optionalRole.map(mapper::toDTO);
    }

    @Override
    public String findRoleIdByUserId(Long id) {
        Optional<Role> optionalRole = repository.findById(repository.findRoleIdByUserId(id));
        String role = optionalRole
                .map(r -> r.getName().name())
                .orElse("ROLE_NOT_FOUND");
        return role;
    }
}
