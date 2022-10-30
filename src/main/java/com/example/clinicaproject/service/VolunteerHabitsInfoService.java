package com.example.clinicaproject.service;

import com.example.clinicaproject.model.VolunteerHabitsInfo;

import java.util.List;
import java.util.List;

public interface VolunteerHabitsInfoService {

    void addVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo);

    void editVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo);

    VolunteerHabitsInfo findVolunteerHabitsInfo(int id);

    List<VolunteerHabitsInfo> allVolunteerHabitsInfo();
}
