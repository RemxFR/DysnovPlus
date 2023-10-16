package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IMetierRepo;
import com.myStreaming.DysnovPlus.entity.Metier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetierService {

    private IMetierRepo iMetierRepo;

    @Autowired
    public MetierService(IMetierRepo iMetierRepo) {
        this.iMetierRepo = iMetierRepo;
    }

    public Metier enregistrerMetier(Metier metier) {
        Metier metierAEnregistrer = null;
        try {
            if (metier != null) {
                metierAEnregistrer = this.iMetierRepo.save(metier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metierAEnregistrer;
    }

    public Metier getMetier(String metier) {
        Metier metierATrouver = null;
        try {
            if (metier != null) {
                Optional<Metier> optionalMetier = this.iMetierRepo.getMetierByName(metier);
                if (optionalMetier.isPresent()) {
                    metierATrouver = optionalMetier.get();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metierATrouver;
    }

    public List<Metier> getMetiers() {
        List<Metier> metiersATrouver = null;
        try {
            metiersATrouver = this.iMetierRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metiersATrouver;
    }

    public Metier modifierMetier(String metier, Metier metierModifie) {
        Metier metierAModifier = null;
        try {
            if (metier != null && metierModifie != null) {
                metierAModifier = this.getMetier(metier);
                metierAModifier.setMetier(metierModifie.getMetier());
                this.iMetierRepo.save(metierAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metierAModifier;
    }

    public boolean supprimerMetier(String metier) {
        Metier metierASupprimer = null;
        boolean estSupprime = false;
        try {
            if (metier != null) {
                metierASupprimer = this.getMetier(metier);
                this.iMetierRepo.delete(metierASupprimer);
                estSupprime = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estSupprime;
    }
}
