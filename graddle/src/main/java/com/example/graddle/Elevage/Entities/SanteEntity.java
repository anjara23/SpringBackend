package com.example.graddle.Elevage.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Entity
@Table(name = "sante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sante;

    //declarer clé etrangère
    @OneToOne
    @JoinColumn(name = "id_animal")
    private AnimalEntity id_animal;

    private boolean vaccin;
    private Date date_vacc;
    private boolean vermifuge;
    private Date date_verm;
    private String maladie;
    private String blessure;
    private String traitement;
    private Date date_trait;
    private Integer etat;
    private Boolean gestation;

}
