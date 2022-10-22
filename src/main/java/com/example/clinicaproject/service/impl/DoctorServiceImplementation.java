package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.Doctor;
import com.example.clinicaproject.model.Role;
import com.example.clinicaproject.repository.DoctorRepository;
import com.example.clinicaproject.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
        doctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
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

//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Doctor doctor = doctorRepository.findByEmail(username);
//        return User.withUsername(doctor.getEmail())
//                .password(doctor.getPassword())
//                .authorities(Set.of(new SimpleGrantedAuthority(Role.DOCTOR.name()))).build();
//    }
}
