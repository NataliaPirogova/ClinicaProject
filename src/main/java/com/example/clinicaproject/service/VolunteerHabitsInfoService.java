package com.example.clinicaproject.service;

import com.example.clinicaproject.model.VolunteerHabitsInfo;
import com.example.clinicaproject.model.enums.Smoking;

import java.util.List;

public interface VolunteerHabitsInfoService {

    void addVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo);

    void editVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo);

    VolunteerHabitsInfo findVolunteerHabitsInfo(int id);

    List<VolunteerHabitsInfo> allVolunteerHabitsInfo();

    List<VolunteerHabitsInfo> findAllBySmoking(Smoking smoking);
//
//    List<VolunteerHabitsInfo> filterBySmoking(@Param("smok") Smoking smok);
}
