package com.example.graddle.Agriculture.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelleRequest {

    private Double latitude;
    private Double longitude;
    private String type_sol;
    private String type_culture_avant;
    private Double surface;
}
