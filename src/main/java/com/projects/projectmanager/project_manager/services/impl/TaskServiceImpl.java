package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.dto.TaskDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import com.projects.projectmanager.project_manager.entities.Project;
import com.projects.projectmanager.project_manager.entities.Task;
import com.projects.projectmanager.project_manager.entities.UserEntity;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.mappers.TaskMapper;
import com.projects.projectmanager.project_manager.repositories.ProjectRepository;
import com.projects.projectmanager.project_manager.repositories.TaskRepository;
import com.projects.projectmanager.project_manager.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final ProjectRepository projectRepository;
    private final UserEntityRepository userEntityRepository;

    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper, ProjectRepository projectRepository, UserEntityRepository userEntityRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskDTO> findAll() {
        List<Task> taskList = (List<Task>) repository.findAll();
        return taskList.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TaskDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Task> optionalTask = repository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        return repository.findById(id).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskDTO> findAllByProjectId(Long projectId){
        List<Task> taskList = (List<Task>) repository.findAllByProjectId(projectId);
        return taskList.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        Project project = projectRepository.findById(taskDTO.getProjectId()).get();
        UserEntity userEntity = userEntityRepository.findById(taskDTO.getUserId()).get();
        Task task = mapper.toEntity(taskDTO,userEntity);
        task.setProject(project);
        return mapper.toDTO(repository.save(task));
    }

    @Transactional
    @Override
    public Optional<TaskDTO> deleteById(Long id) throws ResourceNotFoundException {
        Optional<Task> optionalTask = repository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        repository.deleteById(id);
        return optionalTask.map(mapper::toDTO);
    }
}
