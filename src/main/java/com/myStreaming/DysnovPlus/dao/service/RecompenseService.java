package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IRecompenseRepo;
import com.myStreaming.DysnovPlus.entity.Recompenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecompenseService {

    private IRecompenseRepo recompenseRepo;

    @Autowired
    public RecompenseService(IRecompenseRepo recompenseRepo) {
        this.recompenseRepo = recompenseRepo;
    }

    public Recompenses creerRecompense(Recompenses recompense) {
        Recompenses nouveauRecompense = null;
        return nouveauRecompense;
    }

    public Recompenses modifierRecompense(Long id, Recompenses recompenseModifie) {
        Recompenses recompenseAModifier = null;
        return recompenseAModifier;
    }

    public Recompenses trouverRecompense(Long id) {
        Recompenses recompenseATrouver = null;
        return recompenseATrouver;
    }

    public List<Recompenses> trouverTousLesRecompenses() {
        List<Recompenses> recompenses = null;
        return recompenses;
    }

    public boolean supprimerRecompense(Long id) {
        boolean estSupprime = false;
        return estSupprime;
    }
}
