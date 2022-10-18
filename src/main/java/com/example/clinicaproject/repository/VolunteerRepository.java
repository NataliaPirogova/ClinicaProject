package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    Volunteer findByEmail(String email);
}
