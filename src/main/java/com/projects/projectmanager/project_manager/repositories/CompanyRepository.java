package com.projects.projectmanager.project_manager.repositories;

import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "SELECT c FROM Company c WHERE c.companyNitId = :companyNitId")
    Optional<Company> findByCompanyNitId(Long companyNitId);
}
