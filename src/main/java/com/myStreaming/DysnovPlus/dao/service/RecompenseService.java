package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IRecompenseRepo;
import com.myStreaming.DysnovPlus.entity.Recompenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecompenseService {

    private IRecompenseRepo recompenseRepo;

    @Autowired
    public RecompenseService(IRecompenseRepo recompenseRepo) {
        this.recompenseRepo = recompenseRepo;
    }

    public Recompenses creerRecompense(Recompenses recompense) {
        Recompenses nouvelRecompense = null;
        try {
            nouvelRecompense = this.recompenseRepo.save(recompense);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nouvelRecompense;
    }

    public Recompenses modifierRecompense(int id, Recompenses recompenseModifie) {
        Recompenses recompenseAModifier = null;
        try {
            recompenseAModifier = this.trouverRecompense(id);
            if (recompenseAModifier != null) {
                recompenseAModifier.setRecompense(recompenseModifie.getRecompense());
                this.recompenseRepo.save(recompenseAModifier);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return recompenseAModifier;
    }

    public Recompenses trouverRecompense(int id) {
        Recompenses recompenseATrouver = null;
        try {
            Optional<Recompenses> recompensesOptional = this.recompenseRepo.findById(id);
            if (recompensesOptional.isPresent()) {
                recompenseATrouver = recompensesOptional.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recompenseATrouver;
    }

    public List<Recompenses> trouverTousLesRecompenses() {
        List<Recompenses> recompenses = null;
        try {
            recompenses = this.recompenseRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recompenses;
    }

    public boolean supprimerRecompense(int id) {
        boolean estSupprime = false;
        Recompenses recompense = null;
        try {
            recompense = this.trouverRecompense(id);
            if (recompense != null) {
                this.recompenseRepo.delete(recompense);
                estSupprime = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estSupprime;
    }
}
