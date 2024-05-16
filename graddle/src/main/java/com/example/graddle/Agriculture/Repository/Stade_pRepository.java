package com.example.graddle.Agriculture.Repository;

import com.example.graddle.Agriculture.Entities.Stade_pEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Stade_pRepository extends JpaRepository<Stade_pEntity, Integer> {

    @Query(value = "SELECT etape, date_debut, date_fin, besoin_e FROM public.stade_p WHERE  id_cultiver = :id_cultiver", nativeQuery = true)
    List<Object[]> diagSt(Integer id_cultiver);
}
