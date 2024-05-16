package com.example.graddle.Agriculture.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendrierRequest {

    private Integer id_plante;
    private String activite;
    private Date date_debut;
    private Date date_fin;
    private String description;
}
