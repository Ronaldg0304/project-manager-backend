package com.projects.projectmanager.project_manager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projects.projectmanager.project_manager.entities.audit.Auditable;
import com.projects.projectmanager.project_manager.entities.enums.EState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_task", nullable = false)
    private String name;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double estimatedBudget;
    private Double actualBudget;

    @Enumerated(EnumType.STRING)
    private EState stateTask;

    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
