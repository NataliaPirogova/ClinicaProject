package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.repository.VolunteerRepository;
import com.example.clinicaproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VolunteerServiceImplementation implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerServiceImplementation(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public List<Volunteer> allVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer addVolunteer(Volunteer volunteer) {
//        volunteer.setPassword(new BCryptPasswordEncoder().encode(volunteer.getPassword()));
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void editVolunteer(Volunteer volunteer) {
        volunteerRepository.save(volunteer);
    }

    @Override
    public void deleteVolunteer(Volunteer volunteer) {
        volunteerRepository.delete(volunteer);
    }

    @Override
    public Volunteer getVolunteerByID(int id) {
        return volunteerRepository.findById(id).orElse(new Volunteer());
    }

    @Override
    public Volunteer findByEmail(String email) {
        return volunteerRepository.findByEmail(email);
    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Volunteer volunteer = volunteerRepository.findByEmail(username);
//        return User.withUsername(volunteer.getEmail())
//                .password(volunteer.getPassword())
//                .authorities(Set.of(new SimpleGrantedAuthority(Role.VOLUNTEER.name()))).build();
//    }
}
