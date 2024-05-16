package com.example.graddle.Fermier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.SplittableRandom;

public interface FermierRepository extends JpaRepository<FermierEntity, Integer> {

    @Query(value = "SELECT * FROM public.fermier WHERE nom = :nom  AND mdp = :mdp", nativeQuery = true)
    Optional<FermierEntity> auth(String nom, String mdp);
}
