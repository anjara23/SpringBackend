package com.example.graddle.Fermier;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fermier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FermierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    private String nom_exp;
    private Double taille_exp;
    private String localisation;
}
