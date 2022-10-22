package com.example.clinicaproject.repository;

import com.example.clinicaproject.model.VolunteerHabitsInfo;
import com.example.clinicaproject.model.enums.Smoking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerHabitsInfoRepository extends JpaRepository<VolunteerHabitsInfo, Integer> {

    List<VolunteerHabitsInfo> findAllBySmoking(Smoking smoking);

//    List<VolunteerHabitsInfo> filterBySmoking(@Param("smok") Smoking smok);
}
