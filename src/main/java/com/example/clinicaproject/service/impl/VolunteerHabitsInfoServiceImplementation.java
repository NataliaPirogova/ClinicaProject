package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.VolunteerHabitsInfo;
import com.example.clinicaproject.repository.VolunteerHabitsInfoRepository;
import com.example.clinicaproject.service.VolunteerHabitsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VolunteerHabitsInfoServiceImplementation implements VolunteerHabitsInfoService {

    private final VolunteerHabitsInfoRepository volunteerHabitsInfoRepository;

    @Autowired
    public VolunteerHabitsInfoServiceImplementation(VolunteerHabitsInfoRepository volunteerHabitsInfoRepository) {
        this.volunteerHabitsInfoRepository = volunteerHabitsInfoRepository;
    }

    @Override
    public void addVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo) {
        volunteerHabitsInfoRepository.save(volunteerHabitsInfo);
    }

    @Override
    public void editVolunteerHabitsInfo(VolunteerHabitsInfo volunteerHabitsInfo) {
        volunteerHabitsInfoRepository.save(volunteerHabitsInfo);
    }

    @Override
    public VolunteerHabitsInfo findVolunteerHabitsInfo(int id) {
        return volunteerHabitsInfoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<VolunteerHabitsInfo> allVolunteerHabitsInfo() {
        return volunteerHabitsInfoRepository.findAll();
    }
}