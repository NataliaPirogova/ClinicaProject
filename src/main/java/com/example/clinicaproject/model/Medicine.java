package com.example.clinicaproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String medicineName;
    @ManyToOne
    @JoinColumn(name = "manufacturers_id")
    private MedicineManufacturer manufacturer;
    @OneToMany
    @JoinColumn(name = "volunteer_id")
    private List<Volunteer> volunteer;

}
