package com.example.graddle.Elevage.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "production")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prod;

    private String type_produit;
    private String espece;
    private Integer mois;
    private Double kpi;

}
