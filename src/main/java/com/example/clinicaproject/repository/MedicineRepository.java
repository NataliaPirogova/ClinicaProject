package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
