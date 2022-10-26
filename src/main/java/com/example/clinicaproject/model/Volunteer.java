package com.example.clinicaproject.model;

import com.example.clinicaproject.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private final Role role = Role.VOLUNTEER;
    private String firstName;//имя
    private String lastName;//фамилия
    private String middleName;//отчество
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate doB;//дата рождения
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;//пол
    private long phoneNumber;
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_habits_info_id", referencedColumnName = "id")
    private VolunteerHabitsInfo volunteerHabitsInfo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_primary_health_info_id", referencedColumnName = "id")
    private VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo;
@ManyToOne
@JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private Medicine medicine;
}