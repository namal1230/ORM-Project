package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Therapists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    private String program;
    @OneToMany(mappedBy = "therapists")
    private List<TherapySessions> therapySessions;
    @OneToMany(mappedBy = "therapists")
    private List<Payments> payments;
}
