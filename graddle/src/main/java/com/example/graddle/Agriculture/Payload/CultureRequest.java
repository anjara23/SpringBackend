package com.example.graddle.Agriculture.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CultureRequest {

    private Integer code_parcelle;
    private Integer id_plante;

    private Date date_plantation;
    private Date date_production;
    private Date date_recolte;
    private Double produit_kg;
    private Double nb_planter;
    private Double surface_c;
    private String resultat_c;
}
