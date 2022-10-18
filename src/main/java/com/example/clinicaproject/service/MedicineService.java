package com.example.clinicaproject.service;

import com.example.clinicaproject.model.Medicine;

import java.util.List;

public interface MedicineService {
    Medicine add(Medicine medicine);

    void edit(Medicine medicine);

    void delete(Medicine medicine);

    List<Medicine> allMedicines();

    Medicine getMedicineById(int id);

    int medicineCount();
}
