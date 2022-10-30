package com.example.clinicaproject.service;

import com.example.clinicaproject.model.VolunteerPrimaryHealthInfo;

import java.util.List;
import java.util.List;

public interface VolunteerPrimaryHealthInfoService {
    void addVolunteerPrimaryHealthInfo(VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo);

    void editVolunteerPrimaryHealthInfo(VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo);

    List<VolunteerPrimaryHealthInfo> allVolunteerPrimaryHealthInfo();
}
