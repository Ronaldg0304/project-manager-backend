package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
