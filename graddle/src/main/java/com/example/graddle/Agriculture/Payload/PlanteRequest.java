package com.example.graddle.Agriculture.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanteRequest {

    private String type_plante;
    private String variete;
    private String description;
    private Double nbr_plante;
}
