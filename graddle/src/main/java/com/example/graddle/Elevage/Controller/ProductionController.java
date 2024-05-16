package com.example.graddle.Elevage.Controller;

import com.example.graddle.Elevage.Payload.ProductionRequest;
import com.example.graddle.Elevage.Services.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/elevage/production")
@RequiredArgsConstructor
public class ProductionController {

    private  final ProductionService productionService;

    @GetMapping("/diagKPI")
    public Map<Integer, Double> diagKPI(@RequestBody ProductionRequest productionRequest){
        return productionService.diagKPI(productionRequest);
    }
}
