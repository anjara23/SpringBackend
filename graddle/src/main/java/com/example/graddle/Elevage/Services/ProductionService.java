package com.example.graddle.Elevage.Services;

import com.example.graddle.Elevage.Repository.AnimalRepository;
import com.example.graddle.Elevage.Entities.ProductionEntity;
import com.example.graddle.Elevage.Payload.ProductionRequest;
import com.example.graddle.Elevage.Repository.ProductionRepository;
import com.example.graddle.Elevage.Repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductionService {


    private final ProductionRepository productionRepository;

    private final AnimalRepository animalRepository;

    private final ProduitRepository produitRepository;



    public Map<Integer, Double> diagKPI(ProductionRequest productionRequest) {

        Integer mois = productionRequest.getMois();
        String type_produit = productionRequest.getType_produit();
        String espece = productionRequest.getEspece();

        List<Object[]> monthsAndKPIList = productionRepository.findMonthsAndKPI(mois, espece, type_produit);

        Map<Integer, Double> monthsAndKPI = new HashMap<>();


        for (Object[] entry : monthsAndKPIList) {
            Integer Mois = (Integer) entry[0]; // Mois
            Double kpi = (Double) entry[1]; // KPI

            // Ajouter le mois et le KPI à la map
            monthsAndKPI.put(mois, kpi);
        }

        // Retourner la map contenant les mois et les KPI
        return monthsAndKPI;
    }


    public ProductionEntity addKPI(ProductionRequest productionRequest){

        ProductionEntity production = new ProductionEntity();
            Integer mois = productionRequest.getMois();
            String type_produit = productionRequest.getType_produit();
            String espece = productionRequest.getEspece();

            Double ttlQ = produitRepository.sumQMonth(mois, type_produit);

            Integer ttlAni = animalRepository.aniUntilMonth(mois, espece);
            Double kpi = null;
                if (ttlAni != null && ttlAni > 0) {
                    if (kpi == null) {
                        kpi = 0.0;
                    }
                    kpi += ttlQ / ttlAni;
                } else {
                    kpi = 0.0;
                }


        //ajout table
            production.setType_produit(type_produit);
            production.setEspece(espece);
            production.setMois(mois);
            production.setKpi(kpi);

          ProductionEntity prod =  productionRepository.save(production);

          return prod;

    }


   /* public void updateKPI(ProductionRequest productionRequest ){
        Integer mois = productionRequest.getMois();
        String type_produit = productionRequest.getType_produit();
        String espece = productionRequest.getEspece();

        ProductionEntity existe = productionRepository.findKPI(mois, espece, type_produit);

        if(existe != null){

            Double ttlQ = produitRepository.sumQMonth(mois, type_produit);

            Integer ttlAni = animalRepository.aniUntilMonth(mois, espece);
            Double kpi = null;
            if (ttlAni != null && ttlAni > 0) {
                if (kpi == null) {
                    kpi = 0.0;
                }
                kpi += ttlQ / ttlAni;
            } else {
                kpi = 0.0;
            }

            //modif
            existe.setKpi(kpi);
            productionRepository.save(existe);
        }


    }*/

    public void updateKPI(Integer id_prod,ProductionRequest productionRequest) {
        Integer mois = productionRequest.getMois();
        String type_produit = productionRequest.getType_produit();
        String espece = productionRequest.getEspece();

        // Afficher les paramètres de recherche
        System.out.println("Paramètres de recherche - Mois: " + mois + ", Type de produit: " + type_produit + ", Espèce: " + espece);

        // Recherche de l'enregistrement existant
       Optional<ProductionEntity>  existe = productionRepository.findById(id_prod);

        if (existe.isPresent()) {

            ProductionEntity production = existe.get();
            // Obtenez la somme de la quantité produite pour le mois et le type de produit spécifiés
            Double ttlQ = produitRepository.sumQMonth(mois, type_produit);

            // Obtenez le nombre total d'animaux jusqu'au mois spécifié pour l'espèce donnée
            Integer ttlAni = animalRepository.aniUntilMonth(mois, espece);

            // Afficher les valeurs de `ttlQ` et `ttlAni`
            System.out.println("Somme de la quantité produite (ttlQ): " + ttlQ);
            System.out.println("Nombre total d'animaux (ttlAni): " + ttlAni);

            Double kpi = 0.0;

            if (ttlAni != null && ttlAni > 0 && ttlQ != null) {
                kpi = ttlQ / ttlAni;
            } else {
                System.out.println("Erreur: `ttlQ` ou `ttlAni` est nul ou `ttlAni` est inférieur à 1");
            }

            // Afficher la valeur de `kpi`
            System.out.println("Valeur calculée de KPI: " + kpi);

            // Mettre à jour le KPI dans l'entité existante et sauvegarder
            production.setKpi(kpi);
            production.setType_produit(type_produit);

           ProductionEntity modif = productionRepository.save(production);
           if(modif !=null){
               System.out.println("KPI mis à jour avec succès pour l'enregistrement existant.");

           }

        } else {
            System.out.println("Aucun enregistrement existant trouvé pour les paramètres spécifiés.");
        }
    }

    public void deleteKPI(Integer id_prod){
        Optional<ProductionEntity> pro =productionRepository.findById(id_prod);
        if (!pro.isPresent()) {
            throw new EntityNotFoundException("AnimalEntity with id " + id_prod + " not found");
        }
        ProductionEntity production = pro.get();

        productionRepository.delete(production);
    }






}
