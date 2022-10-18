package com.example.clinicaproject.service;

import com.example.clinicaproject.model.VolunteerHabitsInfo;

import java.util.List;

public interface VolunteerHabitsInfoService {

    void addVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo);

    void editVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo);

    List<VolunteerHabitsInfo> allVolunteerHabitsInfo();
}
