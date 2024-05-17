package com.example.graddle.Fermier;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FermierService {

    private final FermierRepository fermierRepository;

    public void inscrireF (FermierRequest fermierRequest){
        FermierEntity ferm = new FermierEntity();

        ferm.setNom(fermierRequest.getNom());
        ferm.setPrenom(fermierRequest.getPrenom());
        ferm.setMail(fermierRequest.getMail());
        ferm.setMdp(fermierRequest.getMdp());
        ferm.setNom_exp(fermierRequest.getNom_exp());
        ferm.setTaille_exp(fermierRequest.getTaille_exp());
        ferm.setLocalisation(fermierRequest.getLocalisation());

        fermierRepository.save(ferm);
    }

    public ResponseEntity<String> authentificationF(AuthRequest authRequest){

        if (authRequest == null || authRequest.getNom() == null || authRequest.getMdp() == null) {
            throw new IllegalArgumentException("Les données d'authentification sont incomplètes");
        }
        String nom = authRequest.getNom();
        String mdp = authRequest.getMdp();

        Optional<FermierEntity> present = fermierRepository.auth(nom,mdp);
        if(!present.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non trouvé, veuillez réessayer avec des informations valides");
        }

        return ResponseEntity.ok("Authentification réussie");

    }
}
