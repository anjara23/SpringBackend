package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.PlanteEntity;
import com.example.graddle.Agriculture.Payload.PlanteRequest;
import com.example.graddle.Agriculture.Repository.PlanteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanteService {

    private final PlanteRepository planteRepository;


    public void  addPlante(PlanteRequest planteRequest){
        PlanteEntity plante = new PlanteEntity();

        plante.setType_plante(planteRequest.getType_plante());
        plante.setVariete(planteRequest.getVariete());
        plante.setDescription(planteRequest.getDescription());
        plante.setNbr_plante(planteRequest.getNbr_plante());

        planteRepository.save(plante);
    }

    public void updatePlante(Integer id_plante, PlanteRequest planteRequest){
        Optional<PlanteEntity> p = planteRepository.findById(id_plante);
        if (!p.isPresent()){
            throw new EntityNotFoundException("PlanteEntity with id " + id_plante + " not found");
        }

        PlanteEntity plante = p.get();

        plante.setType_plante(planteRequest.getType_plante());
        plante.setVariete(planteRequest.getVariete());
        plante.setDescription(planteRequest.getDescription());
        plante.setNbr_plante(planteRequest.getNbr_plante());

        planteRepository.save(plante);

    }

    public void updateNbP(Integer id_plante, Double x) {
        Optional<PlanteEntity> p = planteRepository.findById(id_plante);
        if (!p.isPresent()) {
            throw new EntityNotFoundException("PlanteEntity with id " + id_plante + " not found");
        }
        PlanteEntity plante = p.get();

        Double nbr = plante.getNbr_plante();
        Double nbr_plante = nbr - x;

        if (nbr_plante <= 0) {
            nbr_plante = 0.0; // Si nbr_plante est négatif, le mettre à 0
        }

        plante.setNbr_plante(nbr_plante);
        planteRepository.save(plante);
    }

    public List<Object[]> getAllPlante(){
        return planteRepository.getAllPlante();
    }

    public List<Object[]> getByTypePlante(String type_plante){
        return planteRepository.getByTypePlante(type_plante);
    }

    public List<Object[]> getByVariete(String variete){
        return planteRepository.getByVariete(variete);
    }

    public void deletePlante(Integer id_plante){
        Optional<PlanteEntity> p = planteRepository.findById(id_plante);
        if (!p.isPresent()){
            throw new EntityNotFoundException("PlanteEntity with id " + id_plante + " not found");
        }

        PlanteEntity plante = p.get();

        planteRepository.delete(plante);
    }

    public List<Object[]> getIdType(){
        return planteRepository.getIdType();
    }

}
