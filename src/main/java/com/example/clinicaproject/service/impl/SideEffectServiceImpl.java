package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;
import com.example.clinicaproject.repository.SideEffectRepository;
import com.example.clinicaproject.service.SideEffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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
    public Set<SideEffect> allSideEffects() {
        return new HashSet<>(sideEffectRepository.findAll());
    }

    @Override
    public void addSideEffect(SideEffect sideEffect) {
        sideEffectRepository.save(sideEffect);
    }

    @Override
    public void editSideEffectList(SideEffect sideEffect) {
        Set<SideEffect> sideEffectListCurrent = (Set<SideEffect>) sideEffectRepository.findAll();
        for (SideEffect s :
                sideEffectListCurrent) {
            if (s.getName().equals(sideEffect.getName())) {
                sideEffectRepository.save(sideEffect);
                break;
            }
        }
        sideEffectListCurrent.add(sideEffect);
    }

    @Override
    public void editSideEffectListForMedicine(SideEffect sideEffect, Medicine medicine) {
        Set<SideEffect> sideEffectListCurrent = sideEffectRepository.findAllByMedicine(medicine);
        sideEffectListCurrent.add(sideEffect);
    }

    @Override
    public void editSideEffectListForVolunteer(SideEffect sideEffect, Volunteer volunteer) {
        Set<SideEffect> sideEffectListCurrent = (Set<SideEffect>) sideEffectRepository.findAllByVolunteerList(volunteer);
        sideEffectListCurrent.add(sideEffect);
    }

    @Override
    public SideEffect findByName(String name) {
        return sideEffectRepository.findByName(name);
    }
}