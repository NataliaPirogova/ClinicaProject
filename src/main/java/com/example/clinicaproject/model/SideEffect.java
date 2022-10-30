package com.example.clinicaproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "side_effects", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class SideEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "side_effects_medicine",
            joinColumns = {@JoinColumn(name = "side_effects_id")},
            inverseJoinColumns = {@JoinColumn(name = "medicine_id")}
    )
    List<Medicine> medicine = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "side_effects_volunteer",
            joinColumns = {@JoinColumn(name = "side_effects_id")},
            inverseJoinColumns = {@JoinColumn(name = "volunteer_id")}
    )
    List<Volunteer> volunteerList = new ArrayList<>();
}
