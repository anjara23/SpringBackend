package com.example.graddle.Elevage.Repository;

import com.example.graddle.Elevage.Entities.SanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface SanteRepository extends JpaRepository<SanteEntity,Integer>{
    @Query(value = "SELECT * FROM public.sante WHERE id_animal = :id_animal", nativeQuery = true)
    List<SanteEntity> findByIdAnimal(Integer id_animal);

}
