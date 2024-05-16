package com.example.graddle.Fermier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FermierRequest {

    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String nom_exp;
    private Double taille_exp;
    private String localisation;
}
