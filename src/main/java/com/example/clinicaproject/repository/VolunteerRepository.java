package com.example.clinicaproject.repository;

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

    Volunteer findByUserV(User user);

    @Query(value = "SELECT * FROM volunteer WHERE volunteer.first_name = :fn or volunteer.last_name = :ln"
//            ", volunteer_habits_info "
//            "WHERE volunteer.id = volunteer_habits_info.volunteer_id"
            ,
            nativeQuery = true)
    List<Volunteer> findMatchAllFL(@Param("fn") String firstName, @Param("ln") String lastName);


    @Query(value = "SELECT * FROM volunteer WHERE volunteer.first_name = :fn"
//            ", volunteer_habits_info "
//            "WHERE volunteer.id = volunteer_habits_info.volunteer_id"
            ,
            nativeQuery = true)
    List<Volunteer> findMatchAllF(@Param("fn") String firstName);

//    @Query(value = "SELECT * FROM volunteer AS v RIGHT JOIN volunteer_habits_info AS vhb ON vhb.volunteer_id = v.id",
//            nativeQuery = true)
//    List<Volunteer> findMatchAll();


    @Query(value = "SELECT * FROM volunteer v" +
            "JOIN volunteer_habits_info vhb ON (vhb.volunteerId = v.id)"
//            +
//            "HAVING v.firstName = :fn and v.lastName = :ln"
            ,
            nativeQuery = true)
    List<Volunteer> findMatchByFirstNameAndLastName(@Param("fn") String firstName, @Param("ln") String lastName);
}
