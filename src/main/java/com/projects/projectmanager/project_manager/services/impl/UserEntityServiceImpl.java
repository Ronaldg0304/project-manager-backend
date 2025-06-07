package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.TaskDTO;
import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.entities.Task;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.mappers.UserEntityMapper;
import com.projects.projectmanager.project_manager.repositories.UserEntityRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService{

    private final UserEntityRepository repository;
    private final UserEntityMapper mapper;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserEntityServiceImpl(UserEntityRepository repository, UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntityDTO> findAll() {
       List<UserEntity> userEntityList = (List<UserEntity>) repository.findAll();
       return userEntityList.stream()
               .map(mapper::toDTO)
               .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntityDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<UserEntity> optionalUserEntity = repository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return optionalUserEntity.map(mapper::toDTO);
    }

    @Transactional
    @Override
    public UserEntityDTO save(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = mapper.toEntity(userEntityDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntityDTO.getPassword()));
        return mapper.toDTO(repository.save(userEntity));
    }

    @Transactional
    @Override
    public Optional<UserEntityDTO> deleteById(Long id) throws ResourceNotFoundException {
        Optional<UserEntity> optionalUserEntity = repository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        repository.deleteById(id);
        return optionalUserEntity.map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntityDTO> findByUserName(String userName) throws UsernameNotFoundException {
        Optional<UserEntity>  optionalUserEntity = repository.findByUsername(userName);
        if (!optionalUserEntity.isPresent()){
            throw new UsernameNotFoundException("username not found");
        }
        return repository.findByUsername(userName).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserEntityDTO> findByDocumentId(Long id) throws ResourceNotFoundException {
        Optional<UserEntity>  optionalUserEntity = repository.findByDocumentId(id);
        if (!optionalUserEntity.isPresent()){
            throw new ResourceNotFoundException("document id not found");
        }
        return repository.findByDocumentId(id).map(mapper::toDTO);
    }




}


