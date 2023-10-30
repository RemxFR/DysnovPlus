package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IRealisateurRepo;
import com.myStreaming.DysnovPlus.entity.ESqlQueryUtils;
import com.myStreaming.DysnovPlus.entity.Metier;
import com.myStreaming.DysnovPlus.entity.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RealisateurService {

    private IRealisateurRepo realisateurRepo;
    private MetierService metierService;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public RealisateurService(IRealisateurRepo realisateurRepo, MetierService metierService) {
        this.realisateurRepo = realisateurRepo;
        this.metierService = metierService;
    }

    public Personne creerRealisateur(Personne realisateur) {
        Personne nouveauRealisateur = null;
        List<Metier> metierListe = new ArrayList<>();
        try {
            if (realisateur.getMetiers() != null) {
                for (int i = 0; i < realisateur.getMetiers().size(); i++) {
                    Metier metier = this.trouverMetierParNom(realisateur.getMetiers().get(i).getMetier());
                    metierListe.add(metier);
                }
                realisateur.setMetiers(metierListe);
            }
            nouveauRealisateur = this.realisateurRepo.save(realisateur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nouveauRealisateur;
    }

    public Personne modifierRealisateur(Long id, Personne realisateurModifie) {
        Personne realisateurAModifier = null;
        try {
            realisateurAModifier = this.trouverRealisateur(id);
            if (realisateurAModifier != null) {
                realisateurAModifier.setAge(realisateurModifie.getAge());
                realisateurAModifier.setMetiers(realisateurModifie.getMetiers());
                realisateurAModifier.setNom(realisateurModifie.getNom());
                realisateurAModifier.setPrenom(realisateurModifie.getPrenom());
                realisateurAModifier.setFilmographie(realisateurModifie.getFilmographie());
                realisateurAModifier.setDateNaissance(realisateurModifie.getDateNaissance());
                realisateurAModifier.setGenrePersonne(realisateurModifie.getGenrePersonne());
                realisateurAModifier.setNationalite(realisateurModifie.getNationalite());
                realisateurAModifier.setRecompenses(realisateurModifie.getRecompenses());
                this.realisateurRepo.save(realisateurAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realisateurAModifier;
    }

    public Personne trouverRealisateur(Long id) {
        Personne realisateurATrouver = null;
        try {
            Optional<Personne> personneOptional = this.realisateurRepo.findById(id);
            if (personneOptional.isPresent()) {
                realisateurATrouver = personneOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realisateurATrouver;
    }

    public List<Personne> trouverTousLesRealisateurs(Integer sqlRowLimit) {
        List<Personne> realisateurs = null;
        try {
            int rowLimit = ESqlQueryUtils.getRowLimit(sqlRowLimit);
            String queryWithSqlRowLimit = ESqlQueryUtils.FIND_PERSON_QUERY.getLabel() + ESqlQueryUtils.REAL_QUERY.getLabel() + ESqlQueryUtils.ROW_LIMIT_LABEL.getLabel() + rowLimit;
            Query query = this.entityManager.createNativeQuery(queryWithSqlRowLimit, Personne.class);
            this.buildRealisateurListe(query, realisateurs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realisateurs;
    }

    public boolean supprimerRealisateur(Long id) {
        boolean estSupprime = false;
        Personne realisateurASupprimer = null;
        try {
            realisateurASupprimer = this.trouverRealisateur(id);
            if (realisateurASupprimer != null) {
                this.realisateurRepo.delete(realisateurASupprimer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estSupprime;
    }

    public List<Personne> trouverRealisateurParNom(String nom, Integer sqlRowLimit) {
        List<Personne> listeRealisateursParNom = new ArrayList<>();
        try {
            int rowLimit = ESqlQueryUtils.getRowLimit(sqlRowLimit);
            String queryWithSqlRowLimit = ESqlQueryUtils.FIND_PERSON_QUERY.getLabel() + ESqlQueryUtils.NOM_QUERY.getLabel() + ESqlQueryUtils.REAL_QUERY.getLabel() + ESqlQueryUtils.ROW_LIMIT_LABEL.getLabel() + rowLimit;
            Query query = this.entityManager.createNativeQuery(queryWithSqlRowLimit, Personne.class);
            this.buildRealisateurListe(query, listeRealisateursParNom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeRealisateursParNom;
    }

    public Personne trouverrealisateurParNomEtPrenom(String nom, String prenom, Integer sqlRowLimit) {
        Personne realisateur = null;
        try {
            int rowLimit = ESqlQueryUtils.getRowLimit(sqlRowLimit);
            String queryWithSqlRowLimit = ESqlQueryUtils.FIND_PERSON_QUERY.getLabel() + ESqlQueryUtils.NOM_PRENOM_QUERY.getLabel() + ESqlQueryUtils.REAL_QUERY.getLabel() + ESqlQueryUtils.ROW_LIMIT_LABEL.getLabel() + rowLimit;
            Query query = this.entityManager.createNativeQuery(queryWithSqlRowLimit, Personne.class);
            realisateur = (Personne) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realisateur;
    }

    private Metier trouverMetierParNom(String metierLabel) {
        Metier metier = null;
        if (metierLabel != null && !metierLabel.equals("")) {
            metier = this.metierService.getMetier(metierLabel);
        }
        return metier;
    }


    private static void buildRealisateurListe(Query query, List<Personne> realisateurs) {
        List<Personne> queryResultList = (List<Personne>) query.getResultList();
        if(!queryResultList.isEmpty()) {
            for (Personne real: queryResultList) {
                Personne realTrouve = Personne.builder()
                        .id(real.getId())
                        .nom(real.getNom())
                        .prenom(real.getPrenom())
                        .age(real.getAge())
                        .dateNaissance(real.getDateNaissance())
                        .genrePersonne(real.getGenrePersonne())
                        .nationalite(real.getNationalite())
                        .metiers(real.getMetiers())
                        .build();
                realisateurs.add(realTrouve);
            }
        }
    }
}
