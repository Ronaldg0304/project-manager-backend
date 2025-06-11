package com.projects.projectmanager.project_manager.entities;

import com.projects.projectmanager.project_manager.entities.audit.Auditable;
import com.projects.projectmanager.project_manager.entities.enums.EState;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "projects")
public class Project extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_project", nullable = false)
    private String name;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Column(precision = 18, scale = 2)
    private BigDecimal estimatedBudget = BigDecimal.ZERO;

    @Column(precision = 18, scale = 2)
    private BigDecimal actualBudget = BigDecimal.ZERO;

    @Column(precision = 18, scale = 2)
    private BigDecimal additionalCosts = BigDecimal.ZERO;

    @Column(precision = 5, scale = 2)
    private BigDecimal taxPercentage = BigDecimal.ZERO;

    @Column(precision = 18, scale = 2)
    private BigDecimal totalCost = calculateTotalCost();

    @Enumerated(EnumType.STRING)
    private EState stateProject;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    /**
     * Calculates the total cost, including additional costs and taxes.
     * @return Total cost as BigDecimal.
     */

    public BigDecimal calculateTotalCost() {
        BigDecimal baseCost = actualBudget != null ? actualBudget : estimatedBudget;
        BigDecimal taxAmount = baseCost.multiply(taxPercentage.divide(BigDecimal.valueOf(100)));
        return baseCost.add(additionalCosts).add(taxAmount);
    }
}
