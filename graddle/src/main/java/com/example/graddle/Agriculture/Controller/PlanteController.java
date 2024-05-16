package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.Payload.PlanteRequest;
import com.example.graddle.Agriculture.Services.PlanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agriculture/plante")
@RequiredArgsConstructor
public class PlanteController {

    private final PlanteService planteService;

    @PostMapping("/addPlante")
    public void addPlante(@RequestBody PlanteRequest planteRequest){
        planteService.addPlante(planteRequest);
    }

    @PutMapping("/update/{id_plante}")
    public void updatePlante(@PathVariable Integer id_plante,@RequestBody PlanteRequest planteRequest){
        planteService.updatePlante(id_plante,planteRequest);
    }

    @GetMapping("/getAllP")
    public List<Object[]> getAllPlante(){
        return planteService.getAllPlante();
    }

    @GetMapping("/getByType/{type_plante}")
    public List<Object[]> getByTypePlante(@PathVariable String type_plante){
        return planteService.getByTypePlante(type_plante);
    }

    @GetMapping("/getByVariete/{variete}")
    public List<Object[]> getByVariete(@PathVariable String variete){
        return planteService.getByVariete(variete);
    }

    //ito ilay sady maka id_plante no maka anle type_plante
    @GetMapping("/getIdType")
    public List<Object[]> getIdType(){
        return planteService.getIdType();
    }

    @DeleteMapping("/delete/{id_plante}")
    public void deletePlante(@PathVariable Integer id_plante){
        planteService.deletePlante(id_plante);
    }


}
