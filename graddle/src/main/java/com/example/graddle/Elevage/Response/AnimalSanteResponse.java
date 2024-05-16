package com.example.graddle.Elevage.Response;

import com.example.graddle.Elevage.Entities.AnimalEntity;
import com.example.graddle.Elevage.Entities.SanteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSanteResponse {
    private AnimalEntity animal;
    private SanteEntity sante;
}
