package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IFilmRepo;
import com.myStreaming.DysnovPlus.entity.Film;
import com.myStreaming.DysnovPlus.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FilmService {

    private IFilmRepo filmRepo;
    private ActeurService acteurService;
    private RealisateurService realisateurService;

    @Autowired
    public FilmService(IFilmRepo filmRepo, ActeurService acteurService, RealisateurService realisateurService) {
        this.filmRepo = filmRepo;
        this.acteurService = acteurService;
        this.realisateurService = realisateurService;
    }

    public Film creerFilm(Film film) {
        Film nouveauFilm = null;
        try {
            nouveauFilm = this.filmRepo.save(film);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nouveauFilm;
    }

    public Film modifierFilm(Long id, Map<Object, Object> filmModifie) {
        Film filmAModifier = null;
        try {
            filmAModifier = this.trouverFilm(id);
            if (filmAModifier != null) {
                Film finalFilmAModifier = filmAModifier;
                filmModifie.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(Film.class, (String) key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, finalFilmAModifier, value);
                });
                this.filmRepo.save(filmAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }

    public Film trouverFilm(Long id) {
        Film filmATrouver = null;
        try {
            Optional<Film> optionalFilm = this.filmRepo.findById(id);
            if (optionalFilm.isPresent()) {
                filmATrouver = optionalFilm.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmATrouver;
    }

    public List<Film> trouverTousLesFilms() {
        List<Film> films = null;
        try {
            return this.filmRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

    public boolean supprimerFilm(Long id) {
        boolean estSupprime = false;
        Film filmASupprimer = this.trouverFilm(id);
        if (filmASupprimer != null) {
            this.filmRepo.delete(filmASupprimer);
            estSupprime = true;
        }
        return estSupprime;
    }

    public List<Film> trouverFilmsParGenre(String genre) {
        List<Film> filmsParGenre = null;
        try {
            filmsParGenre = this.filmRepo.trouverFilmsParGenre(genre);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmsParGenre;
    }


    public List<Film> trouverFilmParPersonne(String nom) {
        List<Film> filmsParGenre = null;
        try {
            filmsParGenre = this.filmRepo.trouverFilmsParPersonne(nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmsParGenre;
    }

    public List<Film> trouverFilmParPersonnes(List<String> nomsPersonnes) {
        List<Film> filmsParGenre = null;
        try {
            filmsParGenre = this.filmRepo.trouverFilmsParPersonnes(nomsPersonnes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmsParGenre;
    }

    public List<Film> trouverFilmsParNom(String nomFilm) {
        List<Film> filmsParGenre = null;
        try {
            filmsParGenre = this.filmRepo.trouverFilmsParNom(nomFilm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmsParGenre;
    }

    public Film ajouterPersonneAuFilm(Long id, Personne personne) {
        Film filmAModifier = null;
        try {
            filmAModifier = this.trouverFilm(id);
            if (filmAModifier != null) {
                filmAModifier.getPersonnes().add(personne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }

    public Film ajouterActeurAuFilm(Long idFilm, Long idActeur) {
        Film filmAModifier = null;
        Personne personneAAjouter = null;
        try {
            personneAAjouter = this.acteurService.trouverActeur(idActeur);
            filmAModifier = this.trouverFilm(idFilm);
            if (filmAModifier != null && personneAAjouter != null) {
                filmAModifier.getPersonnes().add(personneAAjouter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }

    public Film ajouterRealisateurAuFilm(Long idFilm, Long idReal) {
        Film filmAModifier = null;
        Personne personneAAjouter = null;
        try {
            personneAAjouter = this.realisateurService.trouverRealisateur(idReal);
            filmAModifier = this.trouverFilm(idFilm);
            if (filmAModifier != null && personneAAjouter != null) {
                filmAModifier.getPersonnes().add(personneAAjouter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }
}
