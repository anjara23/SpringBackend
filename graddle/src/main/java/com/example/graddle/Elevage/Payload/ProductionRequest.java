package com.example.graddle.Elevage.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductionRequest {

     private String espece;
     private String type_produit;
     private Integer mois ;
}
