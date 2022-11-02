package com.example.clinicaproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "volunteer_primary_health_info")
public class VolunteerPrimaryHealthInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int height;
    private int weight;
    private int pulse;
    private int bloodPressureSystolic;
    private int bloodPressureDiastolic;
    @OneToOne
    private Volunteer volunteer;
}
