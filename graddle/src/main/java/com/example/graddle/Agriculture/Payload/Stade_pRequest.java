package com.example.graddle.Agriculture.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stade_pRequest {

    private Integer id_cultiver;
    private String etape;
    private Date date_debut;
    private Date date_fin;
    private Double besoin_e;
}
