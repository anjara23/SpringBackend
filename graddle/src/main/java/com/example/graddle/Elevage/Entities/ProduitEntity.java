package com.example.graddle.Elevage.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "produit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProduitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_produit;

    private String type_produit;
    private Double quantite;
    private Integer qualite;
    private Date date_prod;
    private String especef;

}
