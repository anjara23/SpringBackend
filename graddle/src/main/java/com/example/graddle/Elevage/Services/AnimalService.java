package com.example.graddle.Elevage.Services;

import com.example.graddle.Elevage.Entities.SanteEntity;
import com.example.graddle.Elevage.Payload.*;
import com.example.graddle.Elevage.Response.AnimalSanteResponse;
import com.example.graddle.Elevage.Entities.AnimalEntity;
import com.example.graddle.Elevage.Entities.ProduitEntity;
import com.example.graddle.Elevage.Repository.AnimalRepository;
import com.example.graddle.Elevage.Repository.ProductionRepository;
import com.example.graddle.Elevage.Repository.SanteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final SanteRepository santeRepository;
    private final ProduitService produitService;

    private final ProductionRepository productionRepository;


    public void addData(AnimalSanteRequest animalSanteRequest) {
        AnimalRequest animalRequest = animalSanteRequest.getAnimalRequest();
        SanteRequest santeRequest = animalSanteRequest.getSanteRequest();

        AnimalEntity ani = new AnimalEntity();
        ani.setEspece(animalRequest.getEspece());
        ani.setRace(animalRequest.getRace());
        ani.setNom(animalRequest.getNom());
        ani.setDatenaiss(animalRequest.getDatenaiss());
        ani.setSexe(animalRequest.getSexe());
        ani.setStatut(animalRequest.getStatut());
        ani.setDate_enre(animalRequest.getDate_enre());
        ani.setDate_vente(animalRequest.getDate_vente());

        ani.setDate_dec(animalRequest.getDate_dec());

        if (ani.getDate_dec() == null) {
            ani.setDate_dec(null);
        }

        Date nais= animalRequest.getDatenaiss();
        Date auj = new Date();

        Integer age = calculateDateDifference(nais, auj);
        ani.setAge(age);
        ani.setPoids(animalRequest.getPoids());

        AnimalEntity saveData = animalRepository.save(ani);

        //ajout Sante
        if (saveData != null) {
            SanteEntity sante = new SanteEntity();
            sante.setId_animal(saveData);


            if (santeRequest.isVaccin()) {
                sante.setDate_vacc(santeRequest.getDate_vacc());
            } else {
                sante.setDate_vacc(null);
            }


            if (santeRequest.isVermifuge()) {
                sante.setDate_verm(santeRequest.getDate_verm());
            } else {
                sante.setDate_verm(null);
            }

            if(animalRequest.getSexe() == "mâle"){
                sante.setGestation(null);
            }
            else {
                sante.setGestation(santeRequest.isGestation());
            }

            if (santeRequest.getMaladie() == null && santeRequest.getBlessure() == null) {

                sante.setTraitement(null);
                sante.setDate_trait(null);
                //en bonne santé le etat
                sante.setEtat(2);
            } else {
                //en traitement le Etat
                sante.setEtat(1);
                sante.setTraitement(santeRequest.getTraitement());
                sante.setDate_trait(santeRequest.getDate_trait());
            }

            sante.setVaccin(santeRequest.isVaccin());
            sante.setVermifuge(santeRequest.isVermifuge());
            sante.setMaladie(santeRequest.getMaladie());
            sante.setBlessure(santeRequest.getBlessure());

            santeRepository.save(sante);
        }

        //ajout produit
        if (ani.getDate_vente() != null  && ani.getDate_dec() == null) {
            ProduitRequest produitRequest = new ProduitRequest();
            //type_produit = satria animal
            produitRequest.setType_produit("animal");
            produitRequest.setQuantite(ani.getPoids());//le poids anle animal no quantité
            produitRequest.setQualite(2);//afaka ovana fa avis perso
            produitRequest.setDate_prod(ani.getDate_vente());
            produitRequest.setEspecef(ani.getEspece());//especef = espèce ni-fournir anle produit
             produitService.addProduit(produitRequest);

        }
    }

    public AnimalSanteResponse updateData(Integer id_animal, AnimalSanteRequest animalSanteRequest) {
            if (animalSanteRequest == null || animalSanteRequest.getAnimalRequest() == null) {
                throw new IllegalArgumentException("AnimalSanteRequest or AnimalRequest cannot be null");
            }

            Optional<AnimalEntity> aniOptional = animalRepository.findById(id_animal);
            if (!aniOptional.isPresent()) {
                throw new EntityNotFoundException("AnimalEntity with id " + id_animal + " not found");
            }
            AnimalEntity ani = aniOptional.get();

            String espece1 = ani.getEspece();

            // Mettre à jour les propriétés de l'animal avec les valeurs de AnimalRequest
            AnimalRequest animalRequest = animalSanteRequest.getAnimalRequest();
            ani.setEspece(animalRequest.getEspece());
            ani.setRace(animalRequest.getRace());
            ani.setNom(animalRequest.getNom());
            ani.setDatenaiss(animalRequest.getDatenaiss());
            ani.setSexe(animalRequest.getSexe());
            ani.setStatut(animalRequest.getStatut());
            ani.setDate_enre(animalRequest.getDate_enre());
            ani.setDate_vente(animalRequest.getDate_vente());
            ani.setDate_dec(animalRequest.getDate_dec());

            Date nais = animalRequest.getDatenaiss();
            Date auj = new Date();
            Integer age = calculateDateDifference(nais, auj);
            ani.setAge(age);

            ani.setPoids(animalRequest.getPoids());


            ani = animalRepository.save(ani);

                    if (ani.getDate_vente() != null  && ani.getDate_dec() == null) {
                        ProduitRequest produitRequest = new ProduitRequest();
                        produitRequest.setType_produit("animal");
                        produitRequest.setQuantite(1.0);//isanle  animal no quantité
                        produitRequest.setQualite(2);//afaka ovana fa avis perso
                        produitRequest.setDate_prod(ani.getDate_vente());
                        produitRequest.setEspecef(ani.getEspece());//especef = espèce ni-fournir anle produit

                            //verifie si cet animal existe déjà comme produit dans la table produit
                            ProduitEntity existe = produitService.find(produitRequest.getType_produit(), produitRequest.getEspecef());
                            if(existe != null ){

                              Integer id_produit =  existe.getId_produit();
                              //incrémente la quantité
                              Double nouvelleQuantite = produitRequest.getQuantite() + existe.getQuantite();
                              produitRequest.setQuantite(nouvelleQuantite);
                              //ze rehetra ntovy anarana taloha
                              produitService.updateEspece(espece1, ani.getEspece());

                              produitRequest.setEspecef(ani.getEspece());

                              produitService.updateProduit(id_produit, produitRequest);
                            }
                            else{
                                produitService.addProduit(produitRequest);
                            }


                    }

            // Récupérer la SanteEntity
            List<SanteEntity> santeList = santeRepository.findByIdAnimal(id_animal);
            if (santeList.isEmpty()) {
                throw new EntityNotFoundException("SanteEntity with id_animal " + id_animal + " not found");
            }

            SanteEntity sante = santeList.get(0);
            SanteRequest santeRequest = animalSanteRequest.getSanteRequest();

            if (santeRequest.isVaccin()) {
                sante.setDate_vacc(santeRequest.getDate_vacc());
            } else {
                sante.setDate_vacc(null);
            }

            if (santeRequest.isVermifuge()) {
                sante.setDate_verm(santeRequest.getDate_verm());
            } else {
                sante.setDate_verm(null);
            }

            if (ani.getSexe().equals("mâle")) {
                sante.setGestation(null);
            } else {
                sante.setGestation(santeRequest.isGestation());
            }

            if (santeRequest.getMaladie() == null && santeRequest.getBlessure() == null) {
                sante.setTraitement(null);
                sante.setDate_trait(null);
                sante.setEtat(2); // Bonne santé
            } else {
                sante.setEtat(1); // En traitement
                sante.setTraitement(santeRequest.getTraitement());
                sante.setDate_trait(santeRequest.getDate_trait());
            }

            sante.setVaccin(santeRequest.isVaccin());
            sante.setVermifuge(santeRequest.isVermifuge());
            sante.setMaladie(santeRequest.getMaladie());
            sante.setBlessure(santeRequest.getBlessure());


            sante = santeRepository.save(sante);


            AnimalSanteResponse response = new AnimalSanteResponse();
            response.setAnimal(ani);


            response.setSante(sante);

            return response;
    }

    public void delete(Integer id_animal){
        List<SanteEntity> optional = santeRepository.findByIdAnimal(id_animal);

        if (!optional.isEmpty()) {
            SanteEntity sante = optional.get(0);
            santeRepository.delete(sante);

        }
        Optional<AnimalEntity> optionalAnimal = animalRepository.findById(id_animal);
        if (optionalAnimal.isPresent()) {
            AnimalEntity animal = optionalAnimal.get();

            animalRepository.delete(animal);
        }

    }

    //calcul age
    public static Integer calculateDateDifference(Date startDate, Date endDate) {
        long diffInMillies = endDate.getTime() - startDate.getTime();

        long diffInDaysLong = diffInMillies / (24 * 60 * 60 * 1000);

        Integer diffInDays = (int) diffInDaysLong;

        return diffInDays;
    }

    // nb total des animaux
    public Integer ttlAni(){
        return animalRepository.ttlAni();
    }


    // nb des animaux morts et liste des animaux morts
    public List<Object[]> getDec(){
        return animalRepository.getDec();
    }

    public Integer nbDec(){
        return animalRepository.nbDec();
    }

    public Integer nbParEspece(String espece){
        return  animalRepository.nbParEspece(espece);
    }

    public List<Object[]> getByEspece(String espece){
        return animalRepository.getByEspece(espece);
    }

    /*nb animal achetés par espèces
    tsy aiko na acheté na Achat le statut any fa raha miova de ary am requête ary am AnimalRepository no manova*/

    public Integer AchatEspece(String espece){
        return  animalRepository.getAchatEspece(espece);
    }

    //nb animaux vendus par espèce
    public  Integer nbVendu(String espece){
        return animalRepository.nbVendu(espece);
    }

    //le liste des vivants
    public List<Object[]> getAllData(){
        return animalRepository.getEssentiel();
    }

    //get Animal by id
    public AnimalEntity getAnimal(Integer id_animal){
        AnimalEntity ani = animalRepository.findById(id_animal).get();
        return ani;
    }

    //info personnels
    public SanteEntity getAnimalSante(Integer id_animal) {
        List<SanteEntity> santeList = santeRepository.findByIdAnimal(id_animal);

        if (!santeList.isEmpty()) {
            return santeList.get(0);
        } else {
            return null;
        }
    }


    //diagramme Croissance
    public Double diagCroiss (CroissanceRequest request){

        if (request.getA() <= request.getB()) {
            throw new IllegalArgumentException("a doit être supérieur à b");
        }
        if (request.getC() <= request.getD()) {
            throw new IllegalArgumentException("c doit être supérieur à d");
        }

        // Calculer le résultat de l'expression (a - b) / (c - d)
        return (request.getA() - request.getB()) / (request.getC() - request.getD());

    }

    //diagramme Animaux
    public List<Object[]> diagAni(String espece){
        return animalRepository.diagAni(espece);
    }


}

