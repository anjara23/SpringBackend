package com.example.graddle.Agriculture.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parcelle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code_parcelle;

    private Double latitude;
    private Double longitude;
    private String type_sol;
    private String type_culture_avant;
    private Double surface;

}
