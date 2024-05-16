package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.*;
import com.example.graddle.Agriculture.Payload.CalendrierRequest;
import com.example.graddle.Agriculture.Payload.CultureRequest;
import com.example.graddle.Agriculture.Payload.ParcelleRequest;
import com.example.graddle.Agriculture.Payload.Stade_pRequest;
import com.example.graddle.Agriculture.Repository.CalendrierRepository;
import com.example.graddle.Agriculture.Repository.CultureRepository;
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

    private final CalendrierService calendrierService;

    private final CalendrierRepository calendrierRepository;

    private final Stade_pService stade_pService;

    private final Stade_pRepository stade_pRepository;

    Map<Integer, Integer> cultureToCalendrierMap = new HashMap<>();

    Map<Integer, Integer> cultureToStadeMap = new HashMap<>();

    public void addCulture(CultureRequest cultureRequest){

        CultureEntity cult = new CultureEntity();

        Integer code_parcelle = cultureRequest.getCode_parcelle() ;
        Integer id_plante = cultureRequest.getId_plante();

        ParcelleEntity parc  = new ParcelleEntity();
        parc.setCode_parcelle(code_parcelle);

        PlanteEntity plante = new PlanteEntity();
        plante.setId_plante(id_plante);

        //ilaina any am calendrier
        String variete = plante.getVariete();
        String type_plante = plante.getType_plante();

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
            Double x = -nb;
            Boolean nbr = planteService.updateNbP(id_plante, x);
            if (!nbr) {
                throw new RuntimeException("Erreur lors de l'ajout du nombre de plantes.");
            }
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

        //mampiditra anle culture any am calendrier
        if(saveData != null){
            CalendrierRequest request = new CalendrierRequest();

            request.setId_plante(id_plante);
            request.setActivite("Cultivation de "+variete);
            request.setDate_debut(cult.getDate_plantation());
            request.setDate_fin(cult.getDate_recolte());
            request.setDescription("Plantation de plante de type "+type_plante+" et de variété "+variete);

            //ajout direct ato amzay azo ny id anle calendrier
            CalendrierEntity calendrier = new CalendrierEntity();

            PlanteEntity p = new PlanteEntity();
            plante.setId_plante(request.getId_plante());

            calendrier.setId_plante(p);
            calendrier.setActivite(request.getActivite());
            calendrier.setDate_debut(request.getDate_debut());
            calendrier.setDate_fin(request.getDate_fin());
            calendrier.setDescription(request.getDescription());

            CalendrierEntity savedCalendrier = calendrierRepository.save(calendrier);

            Integer id_calendrier = savedCalendrier.getId_calendrier();

            cultureToCalendrierMap.put(saveData.getId_cultiver(), id_calendrier);

                    //ajout ao am stade
                    Stade_pRequest rq = new Stade_pRequest();

                    rq.setId_cultiver(saveData.getId_cultiver());
                    rq.setEtape("Germation");
                    rq.setDate_debut(cult.getDate_plantation());

                    //ajout direct

                    Stade_pEntity stade = new Stade_pEntity();

                    Integer id_cultiver = rq.getId_cultiver();

                    CultureEntity cu = new CultureEntity();
                    cu.setId_cultiver(id_cultiver);

                    stade.setCulture(cu);
                    stade.setEtape(rq.getEtape());
                    stade.setDate_debut(rq.getDate_debut());
                    stade.setDate_fin(rq.getDate_fin());
                    stade.setBesoin_e(rq.getBesoin_e());

                    Stade_pEntity st = stade_pRepository.save(stade);

                    Integer id_stade = st.getId_stade();
                    cultureToStadeMap.put(saveData.getId_cultiver(), id_stade);
        }



    }

    //update en général y compris mampiditra récolte
    public void updateCulture(Integer id_cultiver ,CultureRequest cultureRequest){
        Optional<CultureEntity> cu = cultureRepository.findById(id_cultiver);
        if(!cu.isPresent()){
            throw new EntityNotFoundException("CultureEntity with id " + id_cultiver + " not found");
        }
        CultureEntity cult = cu.get();
        Integer code_parcelle = cultureRequest.getCode_parcelle();
        Integer id_plante = cultureRequest.getId_plante();

        ParcelleEntity parc  = new ParcelleEntity();
        parc.setCode_parcelle(code_parcelle);

        PlanteEntity plante = new PlanteEntity();
        plante.setId_plante(id_plante);

        //ilaina any am calendrier
        String variete = plante.getVariete();
        String type_plante = plante.getType_plante();

        cult.setParcelle(parc);
        cult.setPlante(plante);

        cult.setDate_plantation(cultureRequest.getDate_plantation());
        cult.setDate_production(cultureRequest.getDate_production());


        Double nb = cultureRequest.getNb_planter();

            // Modification du nombre de plantes si différent de zéro

            if(nb!= null){
                Double initiale = cult.getNb_planter();

                if(initiale != null){
                    Double diff = nb - initiale;

                    if(diff != 0.0){
                        Double x = -diff;
                        Boolean nbr = planteService.updateNbP(id_plante, x);
                        if (!nbr) {
                            throw new RuntimeException("Erreur lors de la mise à jour du nombre de plantes.");
                        }
                        cult.setNb_planter(nb);
                    }

                    cult.setNb_planter(nb);

                }
            } else if(nb == 0.0) {
                throw new IllegalArgumentException("Le nombre de plantes ne peut pas être null ou égal à zéro.");
            }


        Double s = cultureRequest.getSurface_c();

            // Modification de la surface de la parcelle si non null
            if(s!=null){
                Double sini = cult.getSurface_c();
                if(sini != null){
                    Double dif = s - sini;

                    if(dif != 0.0){
                        Double y = -dif;
                        Boolean surf = parcelleService.updateSurface(code_parcelle, y);
                        if (!surf) {
                            throw new RuntimeException("Erreur lors de la mise à jour de la surface de la parcelle.");
                        }
                        cult.setSurface_c(s);
                    }
                    cult.setSurface_c(s);
                }
            } else if (s == 0.0) {
                Date dateR = cultureRequest.getDate_recolte();
                Double produitKg = cultureRequest.getProduit_kg();
                if(dateR != null && produitKg != null){
                    doRecolte(id_cultiver, cultureRequest);
                }

                throw new RuntimeException("Erreur lors de la mise à jour de la surface de la parcelle.");
            }


       // cult.setResultat_C(cultureRequest.getResultat_C());

        CultureEntity save = cultureRepository.save(cult);

            if(save != null ){
                CalendrierRequest request = new CalendrierRequest();

                request.setId_plante(id_plante);
                request.setActivite("Cultivation de "+variete);
                request.setDate_debut(cult.getDate_plantation());
                request.setDate_fin(cult.getDate_recolte());
                request.setDescription("Plantation de plante de type "+type_plante+" et de variété "+variete);

                Integer id_calendrier = cultureToCalendrierMap.get(id_cultiver);
                calendrierService.updateCalendrier(id_calendrier, request);

                    Stade_pRequest rq = new Stade_pRequest();

                    rq.setDate_debut(cult.getDate_plantation());
                    Integer id_stade = cultureToStadeMap.get(id_cultiver);
                    stade_pService.updateStade(id_stade, rq);

            }

    }

    //raha atao mitokana ny fanaovana récolte
    public void doRecolte(Integer id_cultiver, CultureRequest cultureRequest){
        Optional<CultureEntity> cu = cultureRepository.findById(id_cultiver);
        if(!cu.isPresent()){
            throw new EntityNotFoundException("CultureEntity with id " + id_cultiver + " not found");
        }
        CultureEntity cult = cu.get();

             //ilaina any am calendrier
            Integer id_plante = cultureRequest.getId_plante();

            PlanteEntity plante = new PlanteEntity();
            plante.setId_plante(id_plante);

            String variete = plante.getVariete();
            String type_plante = plante.getType_plante();

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

            Integer id_calendrier = cultureToCalendrierMap.get(id_cultiver);

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
        cultureRepository.delete(cult);
    }

    //get by date
    public List<Object[]> getByMonth(Integer mois){
        return cultureRepository.getByDate(mois);
    }


}
