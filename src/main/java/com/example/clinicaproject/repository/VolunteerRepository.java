package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer>, JpaSpecificationExecutor<Volunteer> {
    Volunteer findByEmail(String email);
}
