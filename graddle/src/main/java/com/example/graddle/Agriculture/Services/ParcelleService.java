package com.example.graddle.Agriculture.Services;

import com.example.graddle.Agriculture.Entities.ParcelleEntity;
import com.example.graddle.Agriculture.Payload.ParcelleRequest;
import com.example.graddle.Agriculture.Repository.ParcelleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParcelleService {

    private final ParcelleRepository parcelleRepository;

    public  void addParcelle(ParcelleRequest parcelleRequest){

        ParcelleEntity parc = new ParcelleEntity();

        parc.setLatitude(parcelleRequest.getLatitude());
        parc.setLongitude(parcelleRequest.getLongitude());
        parc.setType_sol(parcelleRequest.getType_sol());
        parc.setType_culture_avant(parcelleRequest.getType_culture_avant());
        parc.setSurface(parcelleRequest.getSurface());

        parcelleRepository.save(parc);
    }

    public void updateParcelle(Integer code_parcelle,ParcelleRequest parcelleRequest){

        Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
        if(!pa.isPresent()){
            throw new EntityNotFoundException("ParcelleEntity with id " + code_parcelle + " not found");
        }

        ParcelleEntity parc = pa.get();
        Double latitude = parcelleRequest.getLatitude();
        Double longitude = parcelleRequest.getLongitude();
        String type_sol = parcelleRequest.getType_sol();
        String cultu = parcelleRequest.getType_culture_avant();
        Double surface = parcelleRequest.getSurface();


        if(latitude != null){
            parc.setLatitude(latitude);
        }
        if(longitude != null){
            parc.setLongitude(longitude);
        }
        if(type_sol != null){
            parc.setType_sol(type_sol);
        }
        if(cultu != null){
            parc.setType_culture_avant(cultu);
        }
        if(surface != null){
            parc.setSurface(surface);
        }

        parcelleRepository.save(parc);

    }

    public void updateTypeAvant(Integer code_parcelle, String type_avant){
        Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
        if(!pa.isPresent()){
            throw new EntityNotFoundException("ParcelleEntity with id " + code_parcelle + " not found");
        }
        ParcelleEntity parc = pa.get();
        parc.setType_culture_avant(type_avant);

      parcelleRepository.save(parc);
    }

    public Boolean updateSurface(Integer code_parcelle, Double x){
        Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
        if(!pa.isPresent()){
            throw new EntityNotFoundException("AnimalEntity with id " + code_parcelle + " not found");
        }

        ParcelleEntity parc = pa.get();

       Double surf = parc.getSurface();
       Double surface = surf + x ;

       if(surface < 0){
           return false;
       }

       parc.setSurface(surface);
       parcelleRepository.save(parc);

      return true;
    }

    public Double getEspaceL(Integer code_parcelle){
        return  parcelleRepository.getEspaceL(code_parcelle);
    }

    public List<Object[]> getDispo(){ return parcelleRepository.getDispo(); }

    public List<Object[]> getPlanter(){ return parcelleRepository.getPlanter(); }

    public List<Object[]> getAllParc(){ return  parcelleRepository.getAllParc(); }

    public List<Object[]> getByType(String type_sol){ return parcelleRepository.getByType(type_sol); }

    public List<Object[]> getTypeAvant(String type_culture_avant){ return parcelleRepository.getTypeAvant(type_culture_avant); }

    public void deleteParcelle(Integer code_parcelle){
        Optional<ParcelleEntity> pa = parcelleRepository.findById(code_parcelle);
        if(!pa.isPresent()){
            throw new EntityNotFoundException("AnimalEntity with id " + code_parcelle + " not found");
        }

        ParcelleEntity parc = pa.get();
        parcelleRepository.delete(parc);
    }

    public List<Object[]> getCodeType(){
        return parcelleRepository.getCodeType();
    }
}
