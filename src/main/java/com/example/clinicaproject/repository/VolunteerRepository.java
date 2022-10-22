package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer>, JpaSpecificationExecutor<Volunteer> {
    Volunteer findByEmail(String email);

    List<Volunteer> findByFirstName(@Param("volunteer_firstName") String firstName);

    List<Volunteer> findByLastName(String lastName);
}
