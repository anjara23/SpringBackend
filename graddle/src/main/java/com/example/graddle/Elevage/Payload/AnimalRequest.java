package com.example.graddle.Elevage.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequest {

    private String espece;
    private String race;
    private String nom;
    private Date datenaiss;
    private String sexe;
    private String statut;
    private Date date_enre;
    private Date date_vente;
    private Date date_dec;
    private Integer age;
    private Double poids;

}
