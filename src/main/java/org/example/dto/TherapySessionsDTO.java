package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapySessionsDTO {
    private String therapy;
    private String patient;
    private double cost;
    private String description;
}
