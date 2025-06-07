package com.projects.projectmanager.project_manager.entities;

import com.projects.projectmanager.project_manager.entities.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name ="role_name", nullable = false, length = 15)
    private ERole name;


}
