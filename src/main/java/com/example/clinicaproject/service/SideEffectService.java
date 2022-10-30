package com.example.clinicaproject.service;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;

import java.util.List;

public interface SideEffectService {
    void editSideEffect(SideEffect sideEffect);

    List<SideEffect> allSideEffects();

    void addSideEffect(SideEffect sideEffect);

    void editSideEffectSet(SideEffect sideEffect);

    void editSideEffectSetForMedicine(SideEffect sideEffect, Medicine medicine);

    void editSideEffectSetForVolunteer(SideEffect sideEffect, Volunteer volunteer);
    SideEffect findByName(String name);
}