package com.projects.projectmanager.project_manager.services.base;

import org.springframework.stereotype.Service;
import com.projects.projectmanager.project_manager.dto.ProjectDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CostCalculatorProjectService {

    public BigDecimal calcularCostoTotal(ProjectDTO dto) {
        System.out.println(dto.toString());
        BigDecimal base = dto.getActualBudget().add(dto.getAdditionalCosts());
        BigDecimal taxRate = dto.getTaxPercentage().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal iva = base.multiply(taxRate);
        return base.add(iva);
    }

    public BigDecimal calcularIVA(ProjectDTO dto) {
        BigDecimal base = dto.getActualBudget().add(dto.getAdditionalCosts());
        BigDecimal taxRate = dto.getTaxPercentage().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        return base.multiply(taxRate);
    }
}
