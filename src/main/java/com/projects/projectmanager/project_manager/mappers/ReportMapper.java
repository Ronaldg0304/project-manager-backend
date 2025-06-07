package com.projects.projectmanager.project_manager.mappers;

import com.projects.projectmanager.project_manager.dto.ReportDTO;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.Report;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportDTO toDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setTitle(report.getTitle());
        reportDTO.setDescription(report.getDescription());
        reportDTO.setReportDate(report.getReportDate());
        reportDTO.setProjectId(report.getProject().getId());


        return reportDTO;
    }

    public Report toEntity(ReportDTO dto, UserEntity user, Project project) {
        Report report = new Report();
        report.setId(dto.getId());
        report.setTitle(dto.getTitle());
        report.setDescription(dto.getDescription());
        report.setReportDate(dto.getReportDate());
        report.setProject(project);
        report.setUser(user);
        return report;
    }

}
