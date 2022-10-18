package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.MedicineManufacturer;
import com.example.clinicaproject.repository.MedicineManufacturerRepository;
import com.example.clinicaproject.service.MedicineManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MedicineManufacturerServiceImplementation implements MedicineManufacturerService {

    private final MedicineManufacturerRepository medicineManufacturerRepository;

    @Autowired
    public MedicineManufacturerServiceImplementation(MedicineManufacturerRepository medicineManufacturerRepository) {
        this.medicineManufacturerRepository = medicineManufacturerRepository;
    }

    @Override
    public MedicineManufacturer add(MedicineManufacturer medicineManufacturer) {
        return medicineManufacturerRepository.save(medicineManufacturer);
    }

    @Override
    public void edit(MedicineManufacturer medicineManufacturer) {
        medicineManufacturerRepository.save(medicineManufacturer);
    }

    @Override
    public void delete(MedicineManufacturer medicineManufacturer) {
        medicineManufacturerRepository.delete(medicineManufacturer);
    }

    @Override
    public List<MedicineManufacturer> allMedicineManufacturers() {
        return medicineManufacturerRepository.findAll();
    }

    @Override
    public MedicineManufacturer getMedicineManufacturerById(int id) {
        return medicineManufacturerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public int medicineManufacturerCount() {
        return (int) medicineManufacturerRepository.count();
    }
}
