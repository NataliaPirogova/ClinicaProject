package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.MedicineManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineManufacturerRepository extends JpaRepository<MedicineManufacturer, Integer> {
    MedicineManufacturer findByName(String name);
}
