package com.example.graddle.Elevage.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanteRequest {

   // private AnimalEntity id_animal;

    private boolean vaccin;
    private Date date_vacc;
    private boolean vermifuge;
    private Date date_verm;
    private String maladie;
    private String blessure;
    private String traitement;
    private Date date_trait;
    private Integer etat;
    private boolean gestation;
}
