package com.example.clinicaproject.service;

import com.example.clinicaproject.model.MedicineManufacturer;

import java.util.List;

public interface MedicineManufacturerService {
    MedicineManufacturer add(MedicineManufacturer medicineManufacturer);

    void edit(MedicineManufacturer medicineManufacturer);

    void delete(MedicineManufacturer medicineManufacturer);

    List<MedicineManufacturer> allMedicineManufacturers();

    MedicineManufacturer getMedicineManufacturerById(int id);

    int medicineManufacturerCount();

    MedicineManufacturer findByName(String name);
}
