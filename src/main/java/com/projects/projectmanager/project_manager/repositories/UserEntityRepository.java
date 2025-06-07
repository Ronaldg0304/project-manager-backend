package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.dto.UserEntityDTO;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.documentId = :documentId")
    Optional<UserEntity> findByDocumentId(Long documentId);
}
