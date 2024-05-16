package com.example.graddle.Agriculture.Repository;

import com.example.graddle.Agriculture.Entities.PlanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanteRepository extends JpaRepository<PlanteEntity, Integer> {

    @Query(value = "SELECT * FROM public.plante ", nativeQuery = true)
    List<Object[]> getAllPlante();

    @Query(value = "SELECT * FROM public.plante WHERE type_plante = :type_plante ", nativeQuery = true)
    List<Object[]> getByTypePlante(String type_plante);

    @Query(value = "SELECT * FROM public.plante WHERE variete = :variete ", nativeQuery = true)
    List<Object[]> getByVariete(String variete);

    @Query(value = "SELECT id_plante, type_plante FROM public.plante ", nativeQuery = true)
    List<Object[]> getIdType();

    @Query(value = "SELECT type_plante FROM public.plante WHERE id_plante = :id_plante", nativeQuery = true)
    String getByIdP(Integer id_plante);

    @Query(value = "SELECT variete FROM public.plante WHERE id_plante = :id_plante", nativeQuery = true)
    String getVar(Integer id_plante);
}
