package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String theropy;
    private String patient;
    private double payment;
    private String status;
    @ManyToOne
    private Patients patients;
    @ManyToOne
    private Therapists therapists;
}
