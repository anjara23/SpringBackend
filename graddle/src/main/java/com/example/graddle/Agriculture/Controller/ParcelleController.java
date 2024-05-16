package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.Payload.ParcelleRequest;
import com.example.graddle.Agriculture.Services.ParcelleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agriculture/parcelle")
@RequiredArgsConstructor
public class ParcelleController {

    private final ParcelleService parcelleService;

    @PostMapping("/addParcelle")
    public void addParcelle(@RequestBody ParcelleRequest parcelleRequest){
        parcelleService.addParcelle(parcelleRequest);
    }

    @PutMapping("/updateP/{code_parcelle}")
    public void updateParcelle(@PathVariable Integer code_parcelle, @RequestBody ParcelleRequest parcelleRequest){
        parcelleService.updateParcelle(code_parcelle, parcelleRequest);
    }

    @GetMapping("/getEspaceL/{code_parcelle}")
    public Double getEspaceL(@PathVariable Integer code_parcelle){
        return parcelleService.getEspaceL(code_parcelle);
    }

    @GetMapping("/getAll")
    public List<Object[]> getAllParc(){
        return parcelleService.getAllParc();
    }

    @GetMapping("/getByType/{type_sol}")
    public List<Object[]> getByType(@PathVariable String type_sol){
        return parcelleService.getByType(type_sol);
    }

    @GetMapping("/getAvant/{type_culture_avant}")
    public List<Object[]> getTypeAvant(@PathVariable String type_culture_avant){
        return parcelleService.getTypeAvant(type_culture_avant);
    }

    //ito le maka code_parcelle sy type_sol
    @GetMapping("/getCodeType")
    public List<Object[]> getCodeType(){ return parcelleService.getCodeType();}

    @DeleteMapping("/delete/{code_parcelle}")
    public void deleteParcelle(@PathVariable Integer code_parcelle){
       parcelleService.deleteParcelle(code_parcelle);
    }

    @GetMapping("/getDispo")
    public List<Object[]> getDispo(){
        return parcelleService.getDispo();
    }

    @GetMapping("/getPlanter")
    public List<Object[]> getPlanter(){
        return parcelleService.getPlanter();
    }
}
