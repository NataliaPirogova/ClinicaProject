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

    @Query(value = "SELECT * FROM volunteer LEFT JOIN volunteer_habits_info" +
            " ON volunteer.id = volunteer_habits_info.volunteer_id " +
            "WHERE volunteer.gender = :gender " +
            "OR  volunteer_habits_info.smoking = :smoking " +
            "OR volunteer_habits_info.taking_drugs = :takingDrugs " +
            "OR volunteer_habits_info.taking_medicines = :takingMedicines " +
            "OR volunteer_habits_info.is_pregnant_now = :isPregnantNow " +
            "OR volunteer_habits_info.is_planning_pregnancy = :isPlanningPregnancy " +
            "OR volunteer_habits_info.vegetarian = :vegetarian " +
            "OR volunteer_habits_info.taking_hormonal_contraceptives = :takingHormonalContraceptives " +
            "OR volunteer_habits_info.sport = :sport " +
            "OR volunteer_habits_info.alcohol = :alcohol",
            nativeQuery = true)
    List<Volunteer> findMatchAllByFilters(@Param("gender") String gender,
                                          @Param("smoking") String smoking,
                                          @Param("takingDrugs") String takingDrugs,
                                          @Param("takingMedicines") String takingMedicines,
                                          @Param("isPregnantNow") String isPregnantNow,
                                          @Param("isPlanningPregnancy") String isPlanningPregnancy,
                                          @Param("vegetarian") String vegetarian,
                                          @Param("takingHormonalContraceptives") String takingHormonalContraceptives,
                                          @Param("sport") String sport,
                                          @Param("alcohol") String alcohol);


//
//    @Query(value = "SELECT * FROM volunteer WHERE volunteer.first_name = :fn or volunteer.last_name = :ln"
////            ", volunteer_habits_info "
////            "WHERE volunteer.id = volunteer_habits_info.volunteer_id"
//            ,
//            nativeQuery = true)
//    List<Volunteer> findMatchAllFL(@Param("fn") String firstName, @Param("ln") String lastName);
//
//
//    @Query(value = "SELECT * FROM volunteer WHERE volunteer.first_name = :fn"
////            ", volunteer_habits_info "
////            "WHERE volunteer.id = volunteer_habits_info.volunteer_id"
//            ,
//            nativeQuery = true)
//    List<Volunteer> findMatchAllF(@Param("fn") String firstName);

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
