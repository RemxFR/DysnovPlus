package com.myStreaming.DysnovPlus.dao.controller;

import com.myStreaming.DysnovPlus.dao.service.FilmService;
import com.myStreaming.DysnovPlus.entity.Film;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/film")
public class FilmControleur {

    private FilmService filmService;

    @Autowired
    public FilmControleur(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/trouver/{id}")
    private ResponseEntity<Film> trouverFilm(@PathVariable("id") Long id) {
        Film film = null;
        if (id != null && id > 0) {
            film = this.filmService.trouverFilm(id);
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/trouverTous/limit={sqlRowLimit}")
    private ResponseEntity<List<Film>> trouverTousLesFilms(@RequestParam(name = "sqlRowLimit", required = false) Integer sqlRowLimit) {
        List<Film> films = this.filmService.trouverTousLesFilms(sqlRowLimit);
        if (films != null) {
            return new ResponseEntity<>(films, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/trouverFilm/nomFilm={nomFilm}&limit={sqlRowLimit}")
    private ResponseEntity<List<Film>> trouverFilmParNom(@PathVariable("nomFilm") String nomFilm, @RequestParam(name = "sqlRowLimit", required = false) Integer sqlRowLimit) {
        List<Film> films = null;
        if (nomFilm != null && !nomFilm.equals("")) {
            films = this.filmService.trouverFilmsParNom(nomFilm, sqlRowLimit);
            if (films != null) {
                return new ResponseEntity<>(films, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/trouverFilmParPersonne/nom={nom}&limit={sqlRowLimit}")
    private ResponseEntity<List<Film>> trouverFilmParPersonne(@PathVariable("nom") String nom, @RequestParam(name = "sqlRowLimit", required = false) Integer sqlRowLimit) {
        List<Film> films = null;
        if(nom != null && !nom.equals("")) {
            films = this.filmService.trouverFilmParPersonne(nom, sqlRowLimit);
            if(films != null) {
                return new ResponseEntity<>(films, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/trouverFilmParPersonne/limit={sqlRowLimit}")
    private ResponseEntity<List<Film>> trouverFilmParPersonnes(@RequestBody List<Personne> nomsPersonnes, @RequestParam(name = "sqlRowLimit", required = false) Integer sqlRowLimit) {
        List<Film> films = null;
        for (int i = 0; i < nomsPersonnes.size(); i++) {
            System.out.println(nomsPersonnes.get(i).getNom());
        }
        if(nomsPersonnes != null) {
            films = this.filmService.trouverFilmParPersonnes(nomsPersonnes, sqlRowLimit);
            if(films != null) {
                return new ResponseEntity<>(films, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/creer")
    private ResponseEntity<Film> creerFilm(@RequestBody Film film) {
        Film filmACreer = null;
        if (film != null) {
            filmACreer = this.filmService.creerFilm(film);
            return new ResponseEntity<>(filmACreer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/modifier/{id}")
    private ResponseEntity<Film> modifierFilm(@PathVariable("id") Long id, @RequestBody Map<Object, Object> film) {
        Film filmModifie = null;
        if (film != null && id > 0) {
            filmModifie = this.filmService.modifierFilm(id, film);
            return new ResponseEntity<>(filmModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/ajouter-personne/{id}")
    private ResponseEntity<Film> ajouterUnePersonneAuFilm(@PathVariable("id") Long id, @RequestBody Personne personne) {
        Film filmModifie = null;
        if (personne != null && id > 0) {
            filmModifie = this.filmService.ajouterPersonneAuFilm(id, personne);
            return new ResponseEntity<>(filmModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/ajouter-acteur-par-id/filmId={idFilm}&acteurId={idActeur}")
    private ResponseEntity<Film> ajouterUnActeurAuFilm(@PathVariable("idFilm") Long idFilm, @PathVariable("idActeur") Long idActeur) {
        Film filmModifie = null;
        if (idFilm > 0 && idActeur > 0) {
            filmModifie = this.filmService.ajouterActeurAuFilmParId(idFilm, idActeur);
            return new ResponseEntity<>(filmModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/ajouter-realisateur-par-id/filmId={idFilm}&realId={idReal}")
    private ResponseEntity<Film> ajouterUnRealisateurAuFilm(@PathVariable("idFilm") Long idFilm, @PathVariable("idReal") Long idReal) {
        Film filmModifie = null;
        if (idFilm > 0 && idReal > 0) {
            filmModifie = this.filmService.ajouterRealisateurAuFilmParId(idFilm, idReal);
            return new ResponseEntity<>(filmModifie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/supprimer/{id}")
    private ResponseEntity supprimerFilm(@PathVariable("id") Long id) {
        if (id > 0) {
            boolean estSupprime = this.filmService.supprimerFilm(id);
            if (estSupprime) {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
