package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IActeurRepo;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActeurService {
    
    private IActeurRepo acteurRepo;

    @Autowired
    public ActeurService(IActeurRepo acteurRepo) {
        this.acteurRepo = acteurRepo;
    }

    public Personne creerActeur(Personne acteur) {
        Personne nouveauActeur = null;
        return nouveauActeur;
    }

    public Personne modifierActeur(Long id, Personne acteurModifie) {
        Personne acteurAModifier = null;
        return acteurAModifier;
    }

    public Personne trouverActeur(Long id) {
        Personne acteurATrouver = null;
        return acteurATrouver;
    }

    public List<Personne> trouverTousLesActeurs() {
        List<Personne> acteurs = null;
        return acteurs;
    }

    public boolean supprimerActeur(Long id) {
        boolean estSupprime = false;
        return estSupprime;
    }
}
