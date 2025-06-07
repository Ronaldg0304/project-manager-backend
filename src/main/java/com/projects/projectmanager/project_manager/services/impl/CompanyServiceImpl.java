package com.projects.projectmanager.project_manager.services.impl;

import com.projects.projectmanager.project_manager.dto.CompanyDTO;
import com.projects.projectmanager.project_manager.entities.Company;
import com.projects.projectmanager.project_manager.errors.ResourceNotFoundException;
import com.projects.projectmanager.project_manager.mappers.CompanyMapper;
import com.projects.projectmanager.project_manager.repositories.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompanyDTO> findAll() {
        List<Company> companyList = (List<Company>) repository.findAll();
        return companyList.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CompanyDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Company> optionalCompany = repository.findById(id);
        if (!optionalCompany.isPresent()) {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        return repository.findById(id).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CompanyDTO> findByCompanyNitId(Long companyNitId) throws ResourceNotFoundException {
        Optional<Company> optionalCompany = repository.findByCompanyNitId(companyNitId);
        if (!optionalCompany.isPresent()) {
            throw new ResourceNotFoundException("Company not found with nit: " + companyNitId);
        }
        return repository.findByCompanyNitId(companyNitId).map(mapper::toDTO);
    }

    @Transactional
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        Company company = mapper.toEntity(companyDTO);
        return mapper.toDTO(repository.save(company));
    }

    @Transactional
    @Override
    public Optional<CompanyDTO> deleteById(Long id) throws ResourceNotFoundException {
        Optional<Company> optionalCompany = repository.findById(id);
        if (!optionalCompany.isPresent()) {
            throw new ResourceNotFoundException("Company not found with id: " + id);
        }
        repository.deleteById(id);
        return optionalCompany.map(mapper::toDTO);
    }
}
