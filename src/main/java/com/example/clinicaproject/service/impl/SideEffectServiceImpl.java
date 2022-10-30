package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.repository.SideEffectRepository;
import com.example.clinicaproject.service.SideEffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SideEffectServiceImpl implements SideEffectService {

    private final SideEffectRepository sideEffectRepository;

    @Autowired
    public SideEffectServiceImpl(SideEffectRepository sideEffectRepository) {
        this.sideEffectRepository = sideEffectRepository;
    }

    @Override
    public void editSideEffect(SideEffect sideEffect) {
        sideEffectRepository.save(sideEffect);
    }

    @Override
    public List<SideEffect> allSideEffects() {
        return sideEffectRepository.findAll();
    }

    @Override
    public void addSideEffect(SideEffect sideEffect) {
        sideEffectRepository.save(sideEffect);
    }

    @Override
    public void editSideEffectSet(SideEffect sideEffect) {
        List<SideEffect> sideEffectListCurrent = sideEffectRepository.findAll();
        sideEffectListCurrent.add(sideEffect);
    }

    @Override
    public void editSideEffectSetForMedicine(SideEffect sideEffect, Medicine medicine) {
        List<SideEffect> sideEffectListCurrent = sideEffectRepository.findAllByMedicine(medicine);
        sideEffectListCurrent.add(sideEffect);
    }

    @Override
    public void editSideEffectSetForVolunteer(SideEffect sideEffect, Volunteer volunteer) {
        List<SideEffect> sideEffectListCurrent = sideEffectRepository.findAllByVolunteerSet(volunteer);
        sideEffectListCurrent.add(sideEffect);
    }

    @Override
    public SideEffect findByName(String name) {
        return sideEffectRepository.findByName(name);
    }


}
