package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.entities.Company;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.project.id = :projectId")
    List<Task> findAllByProjectId(@Param("projectId") Long projectId);

    Page<Task> findAll(Pageable pageable);
}
