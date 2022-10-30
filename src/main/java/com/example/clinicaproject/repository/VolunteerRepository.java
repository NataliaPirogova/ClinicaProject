package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    Volunteer findByEmail(String email);
    Volunteer findByUserV (User user);
}
