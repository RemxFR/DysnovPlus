package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IActeurRepo;
import com.myStreaming.DysnovPlus.entity.Metier;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActeurService {

    private IActeurRepo acteurRepo;
    private MetierService metierService;

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

    public List<Personne> trouverTousLesActeurs() {
        List<Personne> acteurs = null;
        try {
            acteurs = this.acteurRepo.findAll();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estSupprime;
    }

    public List<Personne> trouverActeurParNom(String nom) {
        List<Personne> listeActeurParNom = new ArrayList<>();
        try {
            Optional<List<Personne>> optionalList = this.acteurRepo.trouverParNom(nom);
            if (optionalList.isPresent()) {
                listeActeurParNom = optionalList.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listeActeurParNom;
    }

    public Personne trouverActeurParNomEtPrenom(String nom, String prenom) {
        Personne acteur = null;
        try {
            Optional<Personne> optional = this.acteurRepo.trouverParNomEtPrenom(nom, prenom);
            if (optional.isPresent()) {
                acteur = optional.get();
            }
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
}
