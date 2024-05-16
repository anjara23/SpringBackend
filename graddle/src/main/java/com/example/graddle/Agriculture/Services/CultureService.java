package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.*;
import com.example.graddle.Agriculture.Payload.CalendrierRequest;
import com.example.graddle.Agriculture.Payload.CultureRequest;
import com.example.graddle.Agriculture.Payload.ParcelleRequest;
import com.example.graddle.Agriculture.Payload.Stade_pRequest;
import com.example.graddle.Agriculture.Repository.CalendrierRepository;
import com.example.graddle.Agriculture.Repository.CultureRepository;
import com.example.graddle.Agriculture.Repository.PlanteRepository;
import com.example.graddle.Agriculture.Repository.Stade_pRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CultureService {

    private final CultureRepository cultureRepository;
    private final ParcelleService parcelleService;
    private final PlanteService planteService;
    private  final PlanteRepository planteRepository;
    private final CalendrierService calendrierService;
    private final CalendrierRepository calendrierRepository;
    private final Stade_pService stade_pService;
    private final Stade_pRepository stade_pRepository;



    public void addCulture(CultureRequest cultureRequest){

        CultureEntity cult = new CultureEntity();

        Integer code_parcelle = cultureRequest.getCode_parcelle() ;
        Integer id_plante = cultureRequest.getId_plante();

        ParcelleEntity parc  = new ParcelleEntity();
        parc.setCode_parcelle(code_parcelle);

        PlanteEntity plante = new PlanteEntity();
        plante.setId_plante(id_plante);

        //ilaina any am calendrier
        String variete = planteRepository.getVar(id_plante);
        String type_plante = planteRepository.getByIdP(id_plante);

            //modif type_avant
            System.out.print(type_plante);
            parcelleService.updateTypeAvant(code_parcelle, type_plante);

        cult.setParcelle(parc);
        cult.setPlante(plante);
        cult.setDate_plantation(cultureRequest.getDate_plantation());
        cult.setDate_production(cultureRequest.getDate_production());
        cult.setDate_recolte(cultureRequest.getDate_recolte());
        cult.setProduit_kg(cultureRequest.getProduit_kg());


        Double nb = cultureRequest.getNb_planter();
            // Modification du nombre de plantes si différent de zéro
            if (nb == null || nb == 0.0) {
                throw new IllegalArgumentException("Le nombre de plantes ne peut pas être null ou égal à zéro.");
            }
            planteService.updateNbP(id_plante, nb);
        cult.setNb_planter(nb);

        Double s = cultureRequest.getSurface_c();
            // Modification de la surface de la parcelle si différente de zéro
            if (s == null || s == 0.0) {
                throw new IllegalArgumentException("La surface ne peut pas être null ou égal à zéro.");
            }
            Double y = -s;
            Boolean surf = parcelleService.updateSurface(code_parcelle, y);
            if (!surf) {
                throw new RuntimeException("Erreur lors de l'ajout de la surface de la parcelle.");
            }
        cult.setSurface_c(s);
        cult.setResultat_c(cultureRequest.getResultat_c());

        CultureEntity saveData = cultureRepository.save(cult);

        //mampiditra anle culture any am calendrier sy stade_P
        if(saveData != null){

            CalendrierEntity calendrier = new CalendrierEntity();

            calendrier.setPlante(plante);
            calendrier.setActivite("Cultivation de "+variete);
            calendrier.setDate_debut(cult.getDate_plantation());
            calendrier.setDate_fin(cult.getDate_recolte());
            calendrier.setDescription("Plantation de plante de type "+type_plante+" et de variété "+variete);

            CalendrierEntity savedCalendrier = calendrierRepository.save(calendrier);

            Integer id_calendrier = savedCalendrier.getId_calendrier();


                    //ajout ao am stade
                    Stade_pEntity stade = new Stade_pEntity();

                    Integer id_cultiver = saveData.getId_cultiver();

                    CultureEntity cu = new CultureEntity();
                    cu.setId_cultiver(id_cultiver);

                    stade.setCulture(cu);
                    stade.setEtape("Germation");
                    stade.setDate_debut(cult.getDate_plantation());

                    Stade_pEntity st = stade_pRepository.save(stade);

                    Integer id_stade = st.getId_stade();
        }



    }

    public void doRecolte(Integer id_cultiver, CultureRequest cultureRequest){
        Optional<CultureEntity> cu = cultureRepository.findById(id_cultiver);
        if(!cu.isPresent()){
            throw new EntityNotFoundException("CultureEntity with id " + id_cultiver + " not found");
        }
        CultureEntity cult = cu.get();

             //ilaina any am calendrier
            Integer id_plante = cult.getPlante().getId_plante();
            String variete = planteRepository.getVar(id_plante);
            String type_plante = planteRepository.getByIdP(id_plante);

            Integer code_parcelle = cult.getParcelle().getCode_parcelle();
            Double nb_cult = cult.getNb_planter();

            Double produit = cultureRequest.getProduit_kg();

        cult.setDate_recolte(cultureRequest.getDate_recolte());
        cult.setProduit_kg(produit);

        //resaka récolte
        if(nb_cult < produit){
            String resultat = "Bonne récolte";
            cult.setResultat_c(resultat);
        }
        else{
            String res = "Mauvaise récolte";
            cult.setResultat_c(res);
        }

        //mamerina anle surface ho 0
        Double s = cult.getSurface_c();
        ParcelleRequest qr = new ParcelleRequest();
        qr.setType_culture_avant(type_plante);
        parcelleService.updateParcelle(code_parcelle, qr);

        Boolean surf = parcelleService.updateSurface(code_parcelle,s);

        cult.setSurface_c(0.0);

       CultureEntity saveR =  cultureRepository.save(cult);

        if(saveR != null){
            CalendrierRequest request = new CalendrierRequest();

            request.setId_plante(id_plante);
            request.setActivite("Cultivation de "+variete);
            request.setDate_debut(cult.getDate_plantation());
            request.setDate_fin(cult.getDate_recolte());
            request.setDescription("Plantation de plante de type "+type_plante+" et de variété "+variete);

            Integer id_calendrier = calendrierRepository.getId(id_plante);

            calendrierService.updateCalendrier(id_calendrier, request);

        }

    }

    public List<Object[]> getAllC(){
        return cultureRepository.getAllC();
    }

    public List<Object[]> getByVariete(String variete){
        return cultureRepository.getByVariete(variete);
    }

    //izay efa namoaka récolte
    public List<Object[]> getFinished(){
        return  cultureRepository.getFinished();
    }

    public void deleteCulture(Integer id_cultiver){
        Optional<CultureEntity> cu = cultureRepository.findById(id_cultiver);
        if(!cu.isPresent()){
            throw new EntityNotFoundException("CultureEntity with id " + id_cultiver + " not found");
        }
        CultureEntity cult = cu.get();

        Integer code_parcelle = cult.getParcelle().getCode_parcelle();

        Integer id_plante = cult.getPlante().getId_plante();

        String type_plante = planteRepository.getByIdP(id_plante);


        Double s = cult.getSurface_c();
        ParcelleRequest qr = new ParcelleRequest();
        qr.setType_culture_avant(type_plante);
        parcelleService.updateParcelle(code_parcelle, qr);

        Boolean surf = parcelleService.updateSurface(code_parcelle,s);

        cult.setSurface_c(0.0);

       cultureRepository.save(cult);

        stade_pService.deleteStade(id_cultiver);

        cultureRepository.delete(cult);
    }

    //get by date
    public List<Object[]> getByMonth(Integer mois){
        return cultureRepository.getByDate(mois);
    }


}
