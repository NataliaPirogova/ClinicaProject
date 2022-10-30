package com.example.clinicaproject.service;

import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.Volunteer;

import java.util.List;

public interface VolunteerService {
    List<Volunteer> allVolunteers();

    Volunteer addVolunteer(Volunteer volunteer);

    void editVolunteer(Volunteer volunteer);

    void deleteVolunteer(Volunteer volunteer);

    Volunteer getVolunteerByID(int id);

    Volunteer findByEmail(String email);
    Volunteer findByUserV (User user);

//    void editSideEffectSet (SideEffect sideEffect);
}
