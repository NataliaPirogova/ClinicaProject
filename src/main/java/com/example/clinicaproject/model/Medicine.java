package com.example.clinicaproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String medicineName;
    @ManyToOne
    @JoinColumn(name = "manufacturers_id")
    private MedicineManufacturer manufacturer;
    @OneToMany(mappedBy = "medicine")
    List<Volunteer> volunteer = new ArrayList<>();
    @ManyToMany(mappedBy = "medicine")
    Set<SideEffect> sideEffect = new HashSet<>();
}
