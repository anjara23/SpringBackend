package com.example.graddle.Elevage.Entities;


import com.example.graddle.Agriculture.Entities.ParcelleEntity;
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

    @ManyToOne(fetch = FetchType.LAZY) // Optional: Set fetch type if needed
    @JoinColumn(name = "id_produit",referencedColumnName = "id_produit", nullable = false) // Not null for foreign key
    private ProduitEntity produit;

    private String type_produit;
    private String espece;
    private Integer mois;
    private Double kpi;

}
