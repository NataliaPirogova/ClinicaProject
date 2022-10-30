package com.example.clinicaproject.model;

import com.example.clinicaproject.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "volunteer_role", joinColumns = @JoinColumn(name = "volunteer_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;
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
    @ManyToMany(mappedBy = "volunteerSet")
    List<SideEffect> sideEffectSet = new ArrayList<>();
}