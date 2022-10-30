package com.example.clinicaproject.service;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;

import java.util.List;
import java.util.List;
import java.util.Set;

public interface SideEffectService {
    void editSideEffect(SideEffect sideEffect);

    Set<SideEffect> allSideEffects();

    void addSideEffect(SideEffect sideEffect);

    void editSideEffectList(SideEffect sideEffect);

    void editSideEffectListForMedicine(SideEffect sideEffect, Medicine medicine);

    void editSideEffectListForVolunteer(SideEffect sideEffect, Volunteer volunteer);
    SideEffect findByName(String name);
}