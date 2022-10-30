package com.example.clinicaproject.service;

import com.example.clinicaproject.model.Doctor;

import java.util.List;
import java.util.List;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    List<Doctor> allDoctors();

    void deleteDoctor(Doctor doctor);

    void editDoctor(Doctor doctor);

    Doctor getById(int id);

    Doctor findByEmail(String email);
}
