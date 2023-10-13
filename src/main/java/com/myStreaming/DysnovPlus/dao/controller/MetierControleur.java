package com.myStreaming.DysnovPlus.dao.controller;

import com.myStreaming.DysnovPlus.dao.service.MetierService;
import com.myStreaming.DysnovPlus.entity.Metier;
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
@RequestMapping("/metier")
public class MetierControleur {

    private MetierService metierService;

    @Autowired
    public MetierControleur(MetierService metierService) {
        this.metierService = metierService;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Metier> ajouterMetier(@RequestBody Metier metier) {
        Metier metierEnregistre = null;
        if (metier != null) {
            metierEnregistre = this.metierService.enregistrerMetier(metier);
        } else {
            return new ResponseEntity<>(metierEnregistre, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(metierEnregistre, HttpStatus.CREATED);
    }

    @GetMapping("/afficher-tout")
    public ResponseEntity<List<Metier>> afficherListeMetiers() {
        List<Metier> metierListe = this.metierService.getMetiers();
        if (metierListe == null) {
            return new ResponseEntity<>(metierListe, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(metierListe, HttpStatus.OK);
    }

    @GetMapping("/trouver/{metier}")
    public ResponseEntity<Metier> trouverMetier(@RequestParam("metier") String metier) {
        Metier metierATrouver = null;
        if (metier != null) {
            metierATrouver = this.metierService.getMetier(metier);
            if (metierATrouver == null) {
                return new ResponseEntity<>(metierATrouver, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(metierATrouver, HttpStatus.OK);
    }

    @PutMapping("/modifier/{nomMetier}")
    public ResponseEntity<Metier> modifierMetier(@RequestBody Metier metierModifie, @RequestParam("nomMetier") String nomMetier) {
        Metier metierAModifie = null;
        if (metierModifie != null && nomMetier != null) {
            metierAModifie = this.metierService.modifierMetier(nomMetier, metierModifie);
            if (metierAModifie == null) {
                return new ResponseEntity<>(metierAModifie, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(metierAModifie, HttpStatus.OK);
    }

    @DeleteMapping("/supprimer/{nomMetier}")
    public ResponseEntity supprimerMetier(@RequestParam("nomMetier") String nomMetier) {
        Metier metierASupprimer = null;
        boolean estSupprimer = false;
        if (nomMetier != null) {
          estSupprimer = this.metierService.supprimerMetier(nomMetier);
        }
        if(!estSupprimer) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
