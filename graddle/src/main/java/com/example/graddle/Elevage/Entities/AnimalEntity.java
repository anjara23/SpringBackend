package com.example.graddle.Elevage.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_animal;

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
