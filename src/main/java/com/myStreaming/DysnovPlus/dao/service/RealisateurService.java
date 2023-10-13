package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IRealisateurRepo;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealisateurService {

    private IRealisateurRepo realisateurRepo;

    @Autowired
    public RealisateurService(IRealisateurRepo realisateurRepo) {
        this.realisateurRepo = realisateurRepo;
    }

    public Personne creerRealisateur(Personne realisateur) {
        Personne nouveauRealisateur = null;
        return nouveauRealisateur;
    }

    public Personne modifierRealisateur(Long id, Personne realisateurModifie) {
        Personne realisateurAModifier = null;
        return realisateurAModifier;
    }

    public Personne trouverRealisateur(Long id) {
        Personne realisateurATrouver = null;
        return realisateurATrouver;
    }

    public List<Personne> trouverTousLesRealisateurs() {
        List<Personne> realisateurs = null;
        return realisateurs;
    }

    public boolean supprimerRealisateur(Long id) {
        boolean estSupprime = false;
        return estSupprime;
    }
}
