package com.example.graddle.Elevage.Repository;

import com.example.graddle.Elevage.Entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<ProduitEntity, Integer> {

    @Query(value = "SELECT id_produit, type_produit, quantite, qualite, date_prod FROM public.produit", nativeQuery = true)
    List<Object[]> getAllProd();

    @Query(value = "SELECT id_produit, type_produit, quantite, qualite, date_prod FROM public.produit WHERE type_produit = :type_produit", nativeQuery = true)
    List<Object[]>getByType(String type_produit);

    @Query(value = "SELECT SUM(p.quantite) FROM public.produit p WHERE EXTRACT(MONTH FROM p.date_prod) = :mois AND type_produit = :type_produit", nativeQuery = true)
    Double sumQMonth(Integer mois, String type_produit);


    @Query(value = "SELECT quantite, qualite, date_prod FROM public.produit WHERE type_produit = :type_produit", nativeQuery = true)
    List<Object[]>diagQ(String type_produit);


    @Query(value = "SELECT *  FROM public.produit WHERE type_produit = :type_produit AND especef = :especef", nativeQuery = true)
    ProduitEntity find(String type_produit, String especef);


    @Query(value = "SELECT *  FROM public.produit WHERE especef = :especef", nativeQuery = true)
    List<ProduitEntity> getByEspece(String especef);


}
