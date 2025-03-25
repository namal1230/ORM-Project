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
    private double cost;
    private String description;
    @ManyToOne
    private Patients patients;
    @ManyToOne
    private Therapists therapists;
    @ManyToOne
    private TherapyPrograms therapyPrograms;

    public TherapySessions(double cost, String description, Patients patient, Therapists therapy, TherapyPrograms program) {
        this.cost=cost;
        this.description=description;
        this.patients=patient;
        this.therapists=therapy;
        this.therapyPrograms=program;
    }
}
