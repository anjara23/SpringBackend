package com.example.graddle.Elevage.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitRequest {


    private String type_produit;
    private Double quantite;
    private Integer qualite;
    private Date date_prod;
    private String especef;
}
