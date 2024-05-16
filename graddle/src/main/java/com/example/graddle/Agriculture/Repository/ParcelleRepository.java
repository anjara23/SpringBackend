package com.example.graddle.Agriculture.Repository;

import com.example.graddle.Agriculture.Entities.ParcelleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ParcelleRepository extends JpaRepository<ParcelleEntity, Integer> {

    @Query(value = "SELECT surface  FROM public.parcelle WHERE code_parcelle = :code_parcelle ", nativeQuery = true)
    Double getEspaceL(Integer code_parcelle);

    @Query(value = "SELECT * FROM public.parcelle ", nativeQuery = true)
    List<Object[]> getAllParc();

    @Query(value = "SELECT * FROM public.parcelle WHERE type_sol = :type_sol ", nativeQuery = true)
    List<Object[]> getByType(String type_sol);

    @Query(value = "SELECT * FROM public.parcelle WHERE type_culture_avant = :type_culture_avant ", nativeQuery = true)
    List<Object[]> getTypeAvant(String type_culture_avant);

    @Query(value = "SELECT code_parcelle, type_sol FROM public.parcelle ", nativeQuery = true)
    List<Object[]> getCodeType();

    @Query(value = "SELECT p.* FROM public.parcelle p LEFT JOIN public.culture c ON p.code_parcelle = c.code_parcelle WHERE c.id_cultiver IS NULL ", nativeQuery = true)
    List<Object[]> getDispo();

    @Query(value = "SELECT p.* FROM public.parcelle p LEFT JOIN public.culture c ON p.code_parcelle = c.code_parcelle WHERE c.id_cultiver IS NOT NULL ", nativeQuery = true)
    List<Object[]> getPlanter();
}
