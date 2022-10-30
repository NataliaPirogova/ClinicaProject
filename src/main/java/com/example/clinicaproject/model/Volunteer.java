package com.example.clinicaproject.model;

import com.example.clinicaproject.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "volunteer", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String firstName;//имя
    private String lastName;//фамилия
    private String middleName;//отчество
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate doB;//дата рождения
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;//пол
    private long phoneNumber;
    @Column(unique = true)
    private String email;
    @OneToOne(mappedBy = "volunteer")
    private VolunteerHabitsInfo volunteerHabitsInfo;
    @OneToOne(mappedBy = "volunteer")
    private VolunteerPrimaryHealthInfo volunteerPrimaryHealthInfo;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    Medicine medicine;
    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    User userV;
    @ManyToMany(mappedBy = "volunteerList")
    Set<SideEffect> sideEffectList = new HashSet<>();
}