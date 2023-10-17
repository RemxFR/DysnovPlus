package com.myStreaming.DysnovPlus.dao.controller;

import com.myStreaming.DysnovPlus.dao.service.ActeurService;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/infos")
    private ResponseEntity<String> getInfos() {
        return new ResponseEntity<>("Tout est ok !", HttpStatus.OK);
    }

    @GetMapping("/trouver/{id}")
    private ResponseEntity<Personne> trouverActeur(@PathVariable("id") Long id) {
        Personne acteurCherche = null;
        if (id != null && id > 0) {
            acteurCherche = this.acteurService.trouverActeur(id);
            return new ResponseEntity<>(acteurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/trouver/{nom}")
    private ResponseEntity<List<Personne>> trouverActeurParNom(@PathVariable("nom") String nom) {
        List<Personne> acteurCherche = null;
        if (nom != null && !nom.equals("")) {
            acteurCherche = this.acteurService.trouverActeurParNom(nom);
            return new ResponseEntity<>(acteurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/trouver/{nom}&{prenom}")
    private ResponseEntity<Personne> trouverActeurParNomEtPrenom(@PathVariable("nom") String nom, @PathVariable String prenom) {
        Personne acteurCherche = null;
        if ((nom != null && !nom.equals("")) && (prenom != null && !prenom.equals(""))) {
            acteurCherche = this.acteurService.trouverActeurParNomEtPrenom(nom, prenom);
            return new ResponseEntity<>(acteurCherche, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/trouver-tous")
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
    private ResponseEntity<Personne> modifierActeur(@PathVariable("id") Long id, @RequestBody Personne acteurModife) {
        Personne acteurAModifie = null;
        if (acteurModife != null) {
            acteurAModifie = this.acteurService.modifierActeur(id, acteurAModifie);
            return new ResponseEntity<>(acteurAModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/supprimer/{id}")
    private ResponseEntity<String> supprimerActeur(@PathVariable("id") Long id) {
        boolean estSupprime = false;
        if (id != null && id > 0) {
           estSupprime = this.acteurService.supprimerActeur(id);
           if(estSupprime) {
               return new ResponseEntity("Suppression réussie", HttpStatus.OK);
           }
        }
        return new ResponseEntity("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
    }
}
