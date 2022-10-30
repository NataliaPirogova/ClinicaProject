package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Medicine;
import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SideEffectRepository extends JpaRepository<SideEffect, Integer> {
    List<SideEffect> findAllByMedicine(Medicine medicine);

    List<SideEffect> findAllByVolunteerSet(Volunteer volunteer);

    SideEffect findByName(String name);
}
