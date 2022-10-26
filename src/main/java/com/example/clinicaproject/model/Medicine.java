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
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String medicineName;
    @ManyToOne
    @JoinColumn(name = "manufacturers_id")
    private MedicineManufacturer manufacturer;
    @OneToMany(mappedBy = "medicine")
    List<Volunteer> volunteer;

}
