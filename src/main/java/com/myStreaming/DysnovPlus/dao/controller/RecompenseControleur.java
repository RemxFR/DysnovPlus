package com.myStreaming.DysnovPlus.dao.controller;

import com.myStreaming.DysnovPlus.dao.service.RecompenseService;
import com.myStreaming.DysnovPlus.entity.Recompenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recompense")
public class RecompenseControleur {

    private RecompenseService recompenseService;

    @Autowired
    public RecompenseControleur(RecompenseService recompenseService) {
        this.recompenseService = recompenseService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Recompenses> ajouterRecompense(@RequestBody Recompenses Recompense) {
        Recompenses recompenseEnregistre = null;
        if (Recompense != null) {
            recompenseEnregistre = this.recompenseService.creerRecompense(recompenseEnregistre);
        } else {
            return new ResponseEntity<>(recompenseEnregistre, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(recompenseEnregistre, HttpStatus.CREATED);
    }

    @GetMapping("/afficher-tout")
    public ResponseEntity<List<Recompenses>> afficherListeRecompenses() {
        List<Recompenses> recompenseListe = this.recompenseService.trouverTousLesRecompenses();
        if (recompenseListe == null) {
            return new ResponseEntity<>(recompenseListe, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(recompenseListe, HttpStatus.OK);
    }

    @GetMapping("/trouver/{id}")
    public ResponseEntity<Recompenses> trouverRecompense(@RequestParam("id") int id) {
        Recompenses recompenseATrouver = null;
        if (id > 0) {
            recompenseATrouver = this.recompenseService.trouverRecompense(id);
            if (recompenseATrouver == null) {
                return new ResponseEntity<>(recompenseATrouver, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(recompenseATrouver, HttpStatus.OK);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Recompenses> modifierRecompense(@RequestBody Recompenses recompenseModifie, @RequestParam("id") int id) {
        Recompenses recompenseAModifie = null;
        if (recompenseModifie != null && id > 0) {
            recompenseAModifie = this.recompenseService.modifierRecompense(id, recompenseModifie);
            if (recompenseAModifie == null) {
                return new ResponseEntity<>(recompenseAModifie, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(recompenseAModifie, HttpStatus.OK);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity supprimerRecompense(@RequestParam("id") int id) {
        Recompenses recompenseASupprimer = null;
        boolean estSupprimer = false;
        if (id > 0) {
            estSupprimer = this.recompenseService.supprimerRecompense(id);
        }
        if(!estSupprimer) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
