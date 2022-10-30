package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SideEffectRepository extends JpaRepository<SideEffect, Integer> {
    Set<SideEffect> findAllByMedicine(Medicine medicine);

    List<SideEffect> findAllByVolunteerList(Volunteer volunteer);

    SideEffect findByName(String name);
}
