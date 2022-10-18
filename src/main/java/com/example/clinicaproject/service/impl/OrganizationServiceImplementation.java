package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.HealthcareOrganization;
import com.example.clinicaproject.repository.HealthcareOrganizationRepository;
import com.example.clinicaproject.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrganizationServiceImplementation implements OrganizationService {

    private final HealthcareOrganizationRepository healthcareOrganizationRepository;

    @Autowired
    public OrganizationServiceImplementation(HealthcareOrganizationRepository healthcareOrganizationRepository) {
        this.healthcareOrganizationRepository = healthcareOrganizationRepository;
    }

    @Override
    public List<HealthcareOrganization> allHealthcareOrganizations() {
        return healthcareOrganizationRepository.findAll();
    }

    @Override
    public void addHealthcareOrganization(HealthcareOrganization healthcareOrganization) {
        healthcareOrganizationRepository.save(healthcareOrganization);
    }

    @Override
    public void deleteHealthcareOrganization(HealthcareOrganization healthcareOrganization) {
        healthcareOrganizationRepository.delete(healthcareOrganization);
    }

    @Override
    public void editHealthcareOrganization(HealthcareOrganization healthcareOrganization) {
        healthcareOrganizationRepository.save(healthcareOrganization);
    }

    @Override
    public HealthcareOrganization getHealthcareOrganizationById(int id) {
        return healthcareOrganizationRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
