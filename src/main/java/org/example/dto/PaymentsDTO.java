package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentsDTO {
    private String theropy;
    private String patient;
    private double payment;
    private String status;
}
