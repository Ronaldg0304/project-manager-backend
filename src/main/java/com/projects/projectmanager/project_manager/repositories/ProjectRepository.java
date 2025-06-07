package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository <Project, Long> {

}
