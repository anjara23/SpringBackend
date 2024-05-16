package com.example.graddle.Agriculture.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plante;

    private String type_plante;
    private String variete;
    private String description;
    private Double nbr_plante;
}
