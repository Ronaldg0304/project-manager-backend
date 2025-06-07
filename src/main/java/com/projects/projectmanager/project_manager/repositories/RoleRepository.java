package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT role_id FROM users_roles WHERE user_id = :userId", nativeQuery = true)
    Long findRoleIdByUserId(@Param("userId") Long userId);
}
