package com.example.graddle.Elevage.Controller;


import com.example.graddle.Elevage.Payload.ProduitRequest;
import com.example.graddle.Elevage.Services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elevage/produit")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping("/addProduit")
    public void addProduit(@RequestBody ProduitRequest produitRequest){
        produitService.addProduit(produitRequest);
    }

    @PutMapping("/update/{id_produit}")
    public void updateProduit(@PathVariable Integer id_produit, @RequestBody ProduitRequest produitRequest){
        produitService.updateProduit(id_produit, produitRequest);
    }

    @DeleteMapping("/delete/{id_produit}")
    public void deleteProduit(@PathVariable Integer id_produit){
        produitService.deleteProduit(id_produit);
    }

    @GetMapping("/getAll")
    public List<Object[]> getAllProd(){
        return produitService.getAllProd();
    }

    @GetMapping("/getByType{type_produit}")
    public List<Object[]> getByType(@PathVariable String type_produit){
        return produitService.getByType(type_produit);
    }

    @GetMapping("/diagQ/{type_prod}")
    public List<Object[]> diagQ( @PathVariable String type_prod){
        return produitService.diagQ(type_prod);
    }
}

