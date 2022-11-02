package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.Doctor;
import com.example.clinicaproject.repository.DoctorRepository;
import com.example.clinicaproject.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImplementation(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> allDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    @Override
    public void editDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor getById(int id) {
        return doctorRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }
}
