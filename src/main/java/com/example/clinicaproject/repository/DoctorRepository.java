package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByEmail(String email);
}
