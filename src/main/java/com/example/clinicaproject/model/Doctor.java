package com.example.clinicaproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String firstName;//имя
    private String lastName;//фамилия
    private String middleName;//отчество
    @Column(unique = true)
    private String email;
    private long phoneNumber;
    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    User userD;
}
