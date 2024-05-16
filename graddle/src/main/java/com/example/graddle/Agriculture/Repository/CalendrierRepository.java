package com.example.graddle.Agriculture.Repository;

import com.example.graddle.Agriculture.Entities.CalendrierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CalendrierRepository extends JpaRepository<CalendrierEntity, Integer> {
//SELECT COUNT(a) FROM public.animal a WHERE EXTRACT(MONTH FROM a.date_enre) <= :mois AND espece = :espece AND date_dec IS NULL

    @Query(value = "SELECT *  FROM public.calendrier WHERE EXTRACT(MONTH FROM calendrier.date_debut) = :mois OR  EXTRACT(MONTH FROM calendrier.date_fin) = :mois ", nativeQuery = true)
    List<Object[]> getByMonth(Integer mois);

    @Query(value = "SELECT * FROM public.calendrier WHERE  calendrier.date_debut = :auj", nativeQuery = true)
    List<CalendrierEntity> getDeb(Date auj);

    @Query(value = "SELECT * FROM public.calendrier WHERE  calendrier.date_fin = :auj", nativeQuery = true)
    List<CalendrierEntity> getFin(Date auj);

    @Query(value = "SELECT id_calendrier FROM public.calendrier WHERE  id_plante = :id_plante", nativeQuery = true)
    Integer getId(Integer id_plante);
}
