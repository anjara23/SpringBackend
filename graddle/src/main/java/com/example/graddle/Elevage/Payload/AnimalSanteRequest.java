package com.example.graddle.Elevage.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSanteRequest {
    private AnimalRequest animalRequest;
    private SanteRequest santeRequest;
}
