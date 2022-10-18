package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.VolunteerPrimaryHealthInfo;
import com.example.clinicaproject.repository.VolunteerPrimaryHealthInfoRepository;
import com.example.clinicaproject.service.VolunteerPrimaryHealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VolunteerPrimaryHealthInfoServiceImplementation implements VolunteerPrimaryHealthInfoService {

    private final VolunteerPrimaryHealthInfoRepository volunteerPrimaryHealthInfoRepository;

    @Autowired
    public VolunteerPrimaryHealthInfoServiceImplementation(VolunteerPrimaryHealthInfoRepository volunteerPrimaryHealthInfoRepository) {
        this.volunteerPrimaryHealthInfoRepository = volunteerPrimaryHealthInfoRepository;
    }

    @Override
    public void addVolunteerPrimaryHealthInfo(VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo) {
        volunteerPrimaryHealthInfoRepository.save(volunteerPrimaryHealthInfo);
    }

    @Override
    public void editVolunteerPrimaryHealthInfo(VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo) {
        volunteerPrimaryHealthInfoRepository.save(volunteerPrimaryHealthInfo);
    }

    @Override
    public List<VolunteerPrimaryHealthInfo> allVolunteerPrimaryHealthInfo() {
        return volunteerPrimaryHealthInfoRepository.findAll();
    }
}
