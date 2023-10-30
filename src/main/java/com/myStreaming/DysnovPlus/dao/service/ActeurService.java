package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IActeurRepo;
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
public class ActeurService {

    private IActeurRepo acteurRepo;
    private MetierService metierService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ActeurService(IActeurRepo acteurRepo, MetierService metierService) {
        this.acteurRepo = acteurRepo;
        this.metierService = metierService;
    }

    public Personne creerActeur(Personne acteur) {
        Personne nouveauActeur = null;
        List<Metier> metierListe = new ArrayList<>();
        try {
            if (acteur.getMetiers() != null) {
                for (int i = 0; i < acteur.getMetiers().size(); i++) {
                    Metier metier = this.trouverMetierParNom(acteur.getMetiers().get(i).getMetier());
                    metierListe.add(metier);
                }
                acteur.setMetiers(metierListe);
            }
            nouveauActeur = this.acteurRepo.save(acteur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nouveauActeur;
    }

    public Personne modifierActeur(Long id, Personne acteurModifie) {
        Personne acteurAModifier = null;
        try {
            acteurAModifier = this.trouverActeur(id);
            if (acteurAModifier != null) {
                acteurAModifier.setAge(acteurModifie.getAge());
                acteurAModifier.setMetiers(acteurModifie.getMetiers());
                acteurAModifier.setNom(acteurModifie.getNom());
                acteurAModifier.setPrenom(acteurModifie.getPrenom());
                acteurAModifier.setFilmographie(acteurModifie.getFilmographie());
                acteurAModifier.setDateNaissance(acteurModifie.getDateNaissance());
                acteurAModifier.setGenrePersonne(acteurModifie.getGenrePersonne());
                acteurAModifier.setNationalite(acteurModifie.getNationalite());
                acteurAModifier.setRecompenses(acteurModifie.getRecompenses());
                this.acteurRepo.save(acteurAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurAModifier;
    }

    public Personne trouverActeur(Long id) {
        Personne acteurATrouver = null;
        try {
            Optional<Personne> personneOptional = this.acteurRepo.findById(id);
            if (personneOptional.isPresent()) {
                acteurATrouver = personneOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurATrouver;
    }

    public List<Personne> trouverTousLesActeurs(Integer sqlRowLimit) {
        List<Personne> acteurs = new ArrayList<>();
        int rowLimit = ESqlQueryUtils.getRowLimit(sqlRowLimit);
        try {
            String queryWithSqlRowLimit = ESqlQueryUtils.FIND_PERSON_QUERY.getLabel() + this.byMetierQuery + ESqlQueryUtils.ROW_LIMIT_LABEL.getLabel() + rowLimit;
            Query query = entityManager.createNativeQuery(queryWithSqlRowLimit, Personne.class);
            this.buildActeursListe(query, acteurs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteurs;
    }

    public boolean supprimerActeur(Long id) {
        boolean estSupprime = false;
        Personne acteurASupprimer = null;
        try {
            acteurASupprimer = this.trouverActeur(id);
            if (acteurASupprimer != null) {
                this.acteurRepo.delete(acteurASupprimer);
                estSupprime = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estSupprime;
    }

    public List<Personne> trouverActeurParNom(String nom, Integer sqlRowLimit) {
        List<Personne> listeActeurParNom = new ArrayList<>();
        try {
            int rowLimit = ESqlQueryUtils.getRowLimit(sqlRowLimit);
            String queryStrg = ESqlQueryUtils.FIND_PERSON_QUERY.getLabel() + ESqlQueryUtils.NOM_QUERY.getLabel() + ESqlQueryUtils.ACTEUR_QUERY.getLabel();
            String queryWithLimit = queryStrg + ESqlQueryUtils.ROW_LIMIT_LABEL.getLabel() + rowLimit;
            Query query = this.entityManager.createNativeQuery(queryWithLimit, Personne.class);
            query.setParameter(1, nom);
            this.buildActeursListe(query, listeActeurParNom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeActeurParNom;
    }



    public Personne trouverActeurParNomEtPrenom(String nom, String prenom, Integer sqlRowLimit) {
        Personne acteur = null;
        try {
            int rowLimit = ESqlQueryUtils.getRowLimit(sqlRowLimit);
            String queryStrg = ESqlQueryUtils.FIND_PERSON_QUERY.getLabel() + ESqlQueryUtils.NOM_PRENOM_QUERY.getLabel() + ESqlQueryUtils.ACTEUR_QUERY.getLabel();
            String queryWithLimit = queryStrg + ESqlQueryUtils.ROW_LIMIT_LABEL.getLabel() + rowLimit;
            Query query = this.entityManager.createNativeQuery(queryWithLimit, Personne.class);
            query.setParameter(1, nom);
            query.setParameter(2, prenom);
            acteur = (Personne) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acteur;
    }

    private Metier trouverMetierParNom(String metierLabel) {
        Metier metier = null;
        if (metierLabel != null && !metierLabel.equals("")) {
            metier = this.metierService.getMetier(metierLabel);
        }
        return metier;
    }

    private static void buildActeursListe(Query query, List<Personne> listeActeurParNom) {
        List<Personne> queryResults = (List<Personne>) query.getResultList();
        if (!queryResults.isEmpty()) {
            for (Personne acteur : queryResults) {
                Personne acteurTrouve = Personne.builder()
                        .id(acteur.getId())
                        .nom(acteur.getNom())
                        .prenom(acteur.getPrenom())
                        .age(acteur.getAge())
                        .dateNaissance(acteur.getDateNaissance())
                        .genrePersonne(acteur.getGenrePersonne())
                        .nationalite(acteur.getNationalite())
                        .metiers(acteur.getMetiers())
                        .build();
                listeActeurParNom.add(acteurTrouve);
            }

        }
    }
}
