package com.example.graddle.Elevage.Repository;


import com.example.graddle.Elevage.Entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity,Integer> {

    @Query(value = "SELECT id_animal, espece, race, nom, datenaiss, sexe, statut, date_enre, date_vente, date_dec, age, poids FROM public.animal WHERE date_dec IS NOT NULL", nativeQuery = true)
    List<Object[]>getDec();

    @Query(value = "SELECT COUNT(id_animal) as ttl FROM public.animal WHERE date_dec IS NOT NULL", nativeQuery = true)
    Integer nbDec();

    @Query(value = "SELECT COUNT(id_animal) as ttl FROM public.animal WHERE espece = :espece AND date_vente IS NOT NULL", nativeQuery = true)
    Integer nbVendu(String espece);

    @Query(value = "SELECT a.id_animal, a.espece, a.nom, a.sexe, a.statut, s.etat FROM public.animal a JOIN public.sante s ON a.id_animal = s.id_animal WHERE date_dec IS  NULL", nativeQuery = true)
    List<Object[]>getEssentiel();

    @Query(value = "SELECT COUNT(id_animal) as ttl FROM public.animal", nativeQuery = true)
    Integer ttlAni();

    @Query(value = "SELECT COUNT(id_animal) FROM public.animal WHERE espece = :espece", nativeQuery = true)
    Integer nbParEspece(String espece);

    @Query(value = "SELECT * FROM public.animal WHERE espece = :espece OR race = :espece OR nom = :espece ", nativeQuery = true)
    List<Object[]>getByEspece(String espece);

    @Query(value = "SELECT COUNT(id_animal) FROM public.animal WHERE espece = :espece AND statut = 'Achat'", nativeQuery = true)
    Integer getAchatEspece(String espece);


    //tokony soloina nouveaux-nés ilay teratany na izay ni-nommer-na anazy de otranzay koa le achat
    @Query(value = "SELECT \n" +
            "    COUNT(CASE WHEN statut = 'Achat' THEN id_animal ELSE NULL END) AS achat,\n" +
            "    COUNT(CASE WHEN date_vente IS NOT NULL THEN id_animal ELSE NULL END) AS vendu,\n" +
            "    COUNT(CASE WHEN date_dec IS NOT NULL THEN id_animal ELSE NULL END) AS deces,\n" +
            "    COUNT(CASE WHEN statut = 'teratany' THEN id_animal ELSE NULL END) AS nouv\n" +
            "FROM \n" +
            "    public.animal WHERE espece = :espece", nativeQuery = true)
    List<Object[]> diagAni(String espece);


    @Query(value = "SELECT COUNT(a) FROM public.animal a WHERE EXTRACT(MONTH FROM a.date_enre) <= :mois AND espece = :espece AND date_dec IS NULL ",nativeQuery = true)
    Integer  aniUntilMonth(Integer mois , String espece);


    @Query(value = "SELECT *  FROM public.animal WHERE  espece = :especeF AND date_dec IS NULL ", nativeQuery = true)
    AnimalEntity Ifnotdec(String especeF);
}
