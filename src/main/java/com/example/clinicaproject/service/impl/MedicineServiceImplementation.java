package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.repository.MedicineRepository;
import com.example.clinicaproject.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicineServiceImplementation implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImplementation(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Medicine add(Medicine medicine) {
        medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public void edit(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @Override
    public void delete(Medicine medicine) {
        medicineRepository.delete(medicine);
    }

    @Override
    public List<Medicine> allMedicines() {
        return (List<Medicine>) medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(int id) {
        return medicineRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public int medicineCount() {
        return (int) medicineRepository.count();
    }

    @Override
    public String probabilityOfSideEffect(Medicine medicine, SideEffect sideEffect) {
        int allV = medicine.getVolunteer().size();
        int sideEffectV = medicine.getVolunteer().stream().filter(s -> s.getSideEffectList().contains(sideEffect)).collect(Collectors.toList()).size();
        double probability = 100 * (sideEffectV / allV);
        System.out.println(probability + " %");
        return probability + " %";
    }
}
