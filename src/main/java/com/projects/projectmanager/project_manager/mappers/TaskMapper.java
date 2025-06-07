package com.projects.projectmanager.project_manager.mappers;

import com.projects.projectmanager.project_manager.dto.ProjectDTO;
import com.projects.projectmanager.project_manager.dto.TaskDTO;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.Task;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.entities.enums.EState;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskDTO dto, UserEntity userEntity) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStartDate(dto.getStartDate());
        task.setEndDate(dto.getEndDate());
        task.setEstimatedBudget(dto.getEstimatedBudget());
        task.setActualBudget(dto.getActualBudget());
        task.setStateTask(EState.valueOf(dto.getStateTask()));
        task.setPriority(dto.getPriority());
        task.setUser(userEntity);

        return task;
    }

    public TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setStartDate(task.getStartDate());
        dto.setEndDate(task.getEndDate());
        dto.setEstimatedBudget(task.getEstimatedBudget());
        dto.setActualBudget(task.getActualBudget());
        dto.setStateTask(task.getStateTask().name());
        dto.setProjectId(task.getProject().getId());
        dto.setPriority(task.getPriority());
        dto.setUserId(task.getUser().getId());

        return dto;
    }

}
