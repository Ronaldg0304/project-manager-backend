package com.projects.projectmanager.project_manager.security;

import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.mappers.UserEntityMapper;
import com.projects.projectmanager.project_manager.repositories.UserEntityRepository;
import com.projects.projectmanager.project_manager.services.impl.UserEntityService;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository repository;
    private final UserEntityMapper mapper;
    private final UserEntityService service;

    public UserDetailsServiceImpl(UserEntityRepository repository, UserEntityMapper mapper, UserEntityService service) {
        this.repository = repository;
        this.mapper = mapper;
        this.service = service;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = service.findByUserName(username).map(mapper::toEntity)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return user;
    }
}
