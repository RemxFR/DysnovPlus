package com.myStreaming.DysnovPlus.dao.controller;

import com.myStreaming.DysnovPlus.dao.service.ActeurService;
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
@RequestMapping("/acteur")
public class ActeurControleur {

    private ActeurService acteurService;

    @Autowired
    public ActeurControleur(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    @GetMapping("trouver/{id}")
    private ResponseEntity<Personne> trouverActeur(@RequestParam("id") Long id) {
        Personne acteurCherche = null;
        if (id != null && id > 0) {
            acteurCherche = this.acteurService.trouverActeur(id);
            return new ResponseEntity<>(acteurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("trouver/{nom}")
    private ResponseEntity<List<Personne>> trouverActeurParNom(@RequestParam("nom") String nom) {
        List<Personne> acteurCherche = null;
        if (nom != null && !nom.equals("")) {
            acteurCherche = this.acteurService.trouverActeurParNom(nom);
            return new ResponseEntity<>(acteurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("trouver/{nom}&{prenom}")
    private ResponseEntity<Personne> trouverActeurParNomEtPrenom(@RequestParam("nom") String nom, @RequestParam String prenom) {
        Personne acteurCherche = null;
        if ((nom != null && !nom.equals("")) && (prenom != null && !prenom.equals(""))) {
            acteurCherche = this.acteurService.trouverActeurParNomEtPrenom(nom, prenom);
            return new ResponseEntity<>(acteurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<List<Personne>> trouverTousLesActeurs() {
        List<Personne> personnes = this.acteurService.trouverTousLesActeurs();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @PostMapping("/creer")
    private ResponseEntity<Personne> creerActeur(@RequestBody Personne acteurCree) {
        Personne nouvelActeur = null;
        if (acteurCree != null) {
            nouvelActeur = this.acteurService.creerActeur(acteurCree);
            return new ResponseEntity<>(nouvelActeur, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/modifier/{id}")
    private ResponseEntity<Personne> modifierActeur(@RequestParam("id") Long id, @RequestBody Personne acteurModife) {
        Personne acteurAModifie = null;
        if (acteurModife != null) {
            acteurAModifie = this.acteurService.modifierActeur(id, acteurAModifie);
            return new ResponseEntity<>(acteurAModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/supprimer/{id}")
    private ResponseEntity supprimerActeur(@RequestParam("id") Long id) {
        boolean estSupprime = false;
        if (id != null && id > 0) {
           estSupprime = this.acteurService.supprimerActeur(id);
           if(estSupprime) {
               return new ResponseEntity(HttpStatus.OK);
           }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
