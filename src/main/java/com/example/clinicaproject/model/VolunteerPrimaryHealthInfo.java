package com.example.clinicaproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class VolunteerPrimaryHealthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int height;
    private int weight;
    private int pulse;
    private int bloodPressureSystolic;
    private int bloodPressureDiastolic;
    @OneToOne(mappedBy = "volunteerPrimaryHealthInfo")
    private Volunteer volunteer;
}
