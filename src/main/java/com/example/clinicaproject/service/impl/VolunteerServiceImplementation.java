package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.repository.VolunteerRepository;
import com.example.clinicaproject.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return (List<Volunteer>) volunteerRepository.findAll();
    }

    @Override
    public Volunteer addVolunteer(Volunteer volunteer) {
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

    @Override
    public Volunteer findByUserV(User user) {
        return volunteerRepository.findByUserV(user);
    }

    @Override
    public List<Volunteer> findMatchByFirstNameAndLastName(String firstName, String lastName) {
        return volunteerRepository.findMatchByFirstNameAndLastName(firstName,lastName);
    }


    @Override
    public List<Volunteer> findMatchAllFL(String firstName, String lastName) {
        return volunteerRepository.findMatchAllFL(firstName, lastName);
    }

    @Override
    public List<Volunteer> findMatchAllF(String firstName) {
        return volunteerRepository.findMatchAllF(firstName);
    }

//    @Override
//    public void editSideEffectList(SideEffect sideEffect) {
//        volunteerRepository.
//    }
}
