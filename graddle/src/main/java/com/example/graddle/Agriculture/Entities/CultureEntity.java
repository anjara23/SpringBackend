package com.example.graddle.Agriculture.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "culture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CultureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cultiver;

    @ManyToOne(fetch = FetchType.LAZY) // Optional: Set fetch type if needed
    @JoinColumn(name = "code_parcelle",referencedColumnName = "code_parcelle", nullable = false) // Not null for foreign key
    private ParcelleEntity parcelle;

    @ManyToOne(fetch = FetchType.LAZY) // Optional: Set fetch type if needed
    @JoinColumn(name = "id_plante", referencedColumnName = "id_plante" ,nullable = false) // Not null for foreign key
    private PlanteEntity plante;

    private Date date_plantation;
    private Date date_production;
    private Date date_recolte;
    private Double produit_kg;
    private Double nb_planter;
    private Double surface_c;
    private String resultat_c;
}
