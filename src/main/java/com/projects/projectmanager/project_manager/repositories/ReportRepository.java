package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);
}
