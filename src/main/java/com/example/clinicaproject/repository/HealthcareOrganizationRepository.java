package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.HealthcareOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthcareOrganizationRepository extends JpaRepository<HealthcareOrganization, Integer> {
}
