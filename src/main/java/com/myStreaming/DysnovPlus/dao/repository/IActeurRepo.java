package com.myStreaming.DysnovPlus.dao.repository;

import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IActeurRepo extends JpaRepository<Personne, Long> {

    @Query("select a from Personne a where a.nom = ?1")
    Optional<List<Personne>> trouverParNom(String nom);

    @Query("select a from Personne a where a.nom = ?1 and a.prenom = ?2")
    Optional<Personne> trouverParNomEtPrenom(String nom, String prenom);
}
