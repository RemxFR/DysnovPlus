package com.myStreaming.DysnovPlus.dao.controller;

import com.myStreaming.DysnovPlus.dao.service.RealisateurService;
import com.myStreaming.DysnovPlus.entity.Personne;
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
@RequestMapping("/real")
public class RealisateurControleur {

    private RealisateurService realisateurService;

    @Autowired
    public RealisateurControleur(RealisateurService realisateurService) {
        this.realisateurService = realisateurService;
    }

    @GetMapping("trouver/{id}")
    private ResponseEntity<Personne> trouverRealisateur(@RequestParam("id") Long id) {
        Personne realisateurCherche = null;
        if (id != null && id > 0) {
            realisateurCherche = this.realisateurService.trouverRealisateur(id);
            return new ResponseEntity<>(realisateurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("trouver/{nom}")
    private ResponseEntity<List<Personne>> trouverRealisateurParNom(@RequestParam("nom") String nom) {
        List<Personne> realisateurCherche = null;
        if (nom != null && !nom.equals("")) {
            realisateurCherche = this.realisateurService.trouverRealisateurParNom(nom);
            return new ResponseEntity<>(realisateurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("trouver/{nom}&{prenom}")
    private ResponseEntity<Personne> trouverRealisateurParNomEtPrenom(@RequestParam("nom") String nom, @RequestParam String prenom) {
        Personne realisateurCherche = null;
        if ((nom != null && !nom.equals("")) && (prenom != null && !prenom.equals(""))) {
            realisateurCherche = this.realisateurService.trouverrealisateurParNomEtPrenom(nom, prenom);
            return new ResponseEntity<>(realisateurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<List<Personne>> trouverTousLesRealisateurs() {
        List<Personne> personnes = this.realisateurService.trouverTousLesRealisateurs();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @PostMapping("/creer")
    private ResponseEntity<Personne> creerRealisateur(@RequestBody Personne realisateurCree) {
        Personne nouvelrealisateur = null;
        if (realisateurCree != null) {
            nouvelrealisateur = this.realisateurService.creerRealisateur(realisateurCree);
            return new ResponseEntity<>(nouvelrealisateur, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/modifier/{id}")
    private ResponseEntity<Personne> modifierRealisateur(@RequestParam("id") Long id, @RequestBody Personne realisateurModife) {
        Personne realisateurAModifie = null;
        if (realisateurModife != null) {
            realisateurAModifie = this.realisateurService.modifierRealisateur(id, realisateurAModifie);
            return new ResponseEntity<>(realisateurAModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/supprimer/{id}")
    private ResponseEntity supprimerRealisateur(@RequestParam("id") Long id) {
        boolean estSupprime = false;
        if (id != null && id > 0) {
            estSupprime = this.realisateurService.supprimerRealisateur(id);
            if(estSupprime) {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
