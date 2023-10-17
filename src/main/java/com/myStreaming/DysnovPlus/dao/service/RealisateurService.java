package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IRealisateurRepo;
import com.myStreaming.DysnovPlus.entity.Metier;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RealisateurService {

    private IRealisateurRepo realisateurRepo;
    private MetierService metierService;

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

    public List<Personne> trouverTousLesRealisateurs() {
        List<Personne> realisateurs = null;
        try {
            realisateurs = this.realisateurRepo.findAll();
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

    public List<Personne> trouverRealisateurParNom(String nom) {
        List<Personne> listeRealisateursParNom = new ArrayList<>();
        try {
            Optional<List<Personne>> optionalList = this.realisateurRepo.trouverParNom(nom);
            if (optionalList.isPresent()) {
                listeRealisateursParNom = optionalList.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeRealisateursParNom;
    }

    public Personne trouverrealisateurParNomEtPrenom(String nom, String prenom) {
        Personne realisateur = null;
        try {
            Optional<Personne> optional = this.realisateurRepo.trouverParNomEtPrenom(nom, prenom);
            if (optional.isPresent()) {
                realisateur = optional.get();
            }
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

}
