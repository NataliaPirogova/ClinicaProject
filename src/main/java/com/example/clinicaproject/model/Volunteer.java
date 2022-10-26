package com.example.clinicaproject.model;

import com.example.clinicaproject.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    @OneToOne(mappedBy = "volunteer")
    private VolunteerHabitsInfo volunteerHabitsInfo;
    @OneToOne(mappedBy = "volunteer")
    private VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    Medicine medicine;
}