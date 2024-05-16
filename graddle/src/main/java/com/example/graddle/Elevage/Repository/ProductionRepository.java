package com.example.graddle.Elevage.Repository;

import com.example.graddle.Elevage.Entities.ProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ProductionRepository extends JpaRepository<ProductionEntity, Integer> {

        @Query(value = "SELECT mois, kpi FROM public.production  WHERE CASE mois = :mois AND espece = :espece AND type_produit =:type_produit", nativeQuery = true)
    List<Object[]> findMonthsAndKPI(Integer mois,String espece , String type_produit);

    @Query(value = "SELECT * FROM public.production WHERE mois = :mois AND espece = :espece AND type_produit = :type_produit", nativeQuery = true)
    ProductionEntity findKPI(Integer mois, String espece, String type_produit);




}
