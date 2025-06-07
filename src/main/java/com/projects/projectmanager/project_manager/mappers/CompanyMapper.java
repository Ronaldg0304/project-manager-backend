package com.projects.projectmanager.project_manager.mappers;

import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyDTO dto) {
        Company company = new Company();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setCompanyNitId(dto.getCompanyNitId());
        company.setEmail(dto.getEmail());
        company.setPhone(dto.getPhone());
        company.setAddress(dto.getAddress());
        return company;
    }

    public CompanyDTO toDTO(Company company) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setCompanyNitId(company.getCompanyNitId());
        dto.setEmail(company.getEmail());
        dto.setPhone(company.getPhone());
        dto.setAddress(company.getAddress());
        return dto;
    }
}
