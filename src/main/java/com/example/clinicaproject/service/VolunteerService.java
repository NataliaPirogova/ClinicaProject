package com.example.clinicaproject.service;

import com.example.clinicaproject.model.Volunteer;

import java.util.List;

public interface VolunteerService {
    List<Volunteer> allVolunteers();

    Volunteer addVolunteer(Volunteer volunteer);

    void editVolunteer(Volunteer volunteer);

    void deleteVolunteer(Volunteer volunteer);

    Volunteer getVolunteerByID(int id);

    List<Volunteer> findByFirstName(String firstName);

}
