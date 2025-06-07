package com.projects.projectmanager.project_manager.mappers;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.entities.enums.EState;
import com.projects.projectmanager.project_manager.services.base.CostCalculatorProjectService;
import org.springframework.stereotype.Component;



@Component
public class ProjectMapper {

    private final CostCalculatorProjectService costCalculatorProjectService;

    public ProjectMapper(CostCalculatorProjectService costCalculatorProjectService) {
        this.costCalculatorProjectService = costCalculatorProjectService;
    }

    public Project toEntity(ProjectDTO dto, Company company, UserEntity userEntity) {

        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setEstimatedBudget(dto.getEstimatedBudget());
        project.setActualBudget(dto.getActualBudget());
        project.setAdditionalCosts(dto.getAdditionalCosts());
        project.setTaxPercentage(dto.getTaxPercentage());
        project.setTotalCost(costCalculatorProjectService.calcularCostoTotal(dto));
        project.setStateProject(EState.valueOf(dto.getStateProject()));
        project.setCompany(company);
        project.setUser(userEntity);

        return project;
    }

    public ProjectDTO toDTO (Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setEstimatedBudget(project.getEstimatedBudget());
        dto.setActualBudget(project.getActualBudget());
        dto.setAdditionalCosts(project.getAdditionalCosts());
        dto.setTaxPercentage(project.getTaxPercentage());
        dto.setTotalCost(project.getTotalCost());
        dto.setStateProject(project.getStateProject().name());
        dto.setCompanyId(project.getCompany().getId());
        dto.setUserId(project.getUser().getId());

        return dto;
    }
}
