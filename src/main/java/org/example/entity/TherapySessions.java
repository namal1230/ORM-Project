package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TherapySessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String therapy;
    private String patient;
    private double cost;
    private String description;
    @ManyToOne
    private Patients patients;
    @ManyToOne
    private Therapists therapists;
    @ManyToOne
    private TherapyPrograms therapyPrograms;
}
