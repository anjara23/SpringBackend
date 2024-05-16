package com.example.graddle.Agriculture.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "calendrier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendrierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_calendrier;

    @OneToOne
    @JoinColumn(name = "id_plante")
    private PlanteEntity plante;

    private String activite;
    private Date date_debut;
    private Date date_fin;
    private String description;
}
