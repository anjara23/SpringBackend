package com.example.graddle.Elevage.Controller;

import com.example.graddle.Elevage.Entities.SanteEntity;
import com.example.graddle.Elevage.Response.AnimalSanteResponse;
import com.example.graddle.Elevage.Entities.AnimalEntity;
import com.example.graddle.Elevage.Payload.AnimalSanteRequest;
import com.example.graddle.Elevage.Payload.CroissanceRequest;
import com.example.graddle.Elevage.Services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/elevage/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("/add")
    public void addData(@RequestBody AnimalSanteRequest animalSanteRequest){
       animalService.addData(animalSanteRequest);
    }

    @PutMapping("/update/{id_animal}")
    public AnimalSanteResponse updateData(@PathVariable Integer id_animal, @RequestBody AnimalSanteRequest animalSanteRequest){
        return animalService.updateData(id_animal, animalSanteRequest);
    }

    @GetMapping("/get/{id_animal}")
    public AnimalEntity getAnimal(@PathVariable Integer id_animal){
        return animalService.getAnimal(id_animal);
    }

    @GetMapping("/getAll")
    public List<Object[]>getAllData(){
        return animalService.getAllData();
    }

    @GetMapping("/about/{id_animal}")
    public SanteEntity getAnimalSante(@PathVariable Integer id_animal){
        return animalService.getAnimalSante(id_animal);
    }

    @DeleteMapping("/delete/{id_animal}")
    public void delete(@PathVariable Integer id_animal){
        animalService.delete(id_animal);
    }


    //ttl y compris les morts
    @GetMapping("/getTTl")
    public Integer ttlAni(){
        return animalService.ttlAni();
    }

    @GetMapping("/getDec")
    public List<Object[]> getDec(){
        return animalService.getDec();
    }

    @GetMapping("/getnbDec")
    public Integer nbDec(){ return  animalService.nbDec();}

    @GetMapping("/getByEspece/{espece}")
    public Integer nbParEspece(@PathVariable String espece){ return  animalService.nbParEspece(espece); }

    @GetMapping("/AchatEspece/{espece}")
    public Integer AchatEspece( @PathVariable String espece){
        return animalService.AchatEspece(espece);
    }

    @GetMapping("/nbVendu/{espece}")
    public  Integer nbVendu( @PathVariable String espece){ return animalService.nbVendu(espece); }

    @GetMapping("/diagC")
    public Double diagCroiss ( @RequestBody CroissanceRequest request){ return  animalService.diagCroiss(request); }

    @GetMapping("/diagAni/{espece}")
    public List<Object[]> diagAni( @PathVariable String espece){ return  animalService.diagAni(espece); }

    //ito le na nom na race na espece de rechercheny daoly
    @GetMapping("/getBy/{espece}")
    public List<Object[]> getByEspece(@PathVariable String espece){
        return animalService.getByEspece(espece);
    }
}


