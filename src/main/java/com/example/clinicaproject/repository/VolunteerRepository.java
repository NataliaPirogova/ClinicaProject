package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.SideEffect;
import com.example.clinicaproject.model.User;
import com.example.clinicaproject.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    Volunteer findByEmail(String email);
    Volunteer findByUserV (User user);

    @Query(value = "SELECT * FROM volunteer AS v" +
            "JOIN volunteer_habits_info ON (volunteer_habits_info.volunteer_id = volunteer.id)" +
            "HAVING v.first_name = :fn and v.last_name = :ln",
            nativeQuery = true)
    List<Volunteer> findMatchByFirstNameAndLastName(@Param("fn") String firstName, @Param("ln") String lastName);
}
