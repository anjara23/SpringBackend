package com.example.graddle.Agriculture.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "stade_p")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stade_pEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_stade;

    @OneToOne
    @JoinColumn(name = "id_cultiver")
    private CultureEntity culture;

    private String etape;
    private Date date_debut;
    private Date date_fin;
    private Double besoin_e;
}
