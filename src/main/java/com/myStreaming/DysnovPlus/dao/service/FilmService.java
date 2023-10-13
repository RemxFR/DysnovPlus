package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IFilmRepo;
import com.myStreaming.DysnovPlus.entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private IFilmRepo filmRepo;

    @Autowired
    public FilmService(IFilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    public Film creerFilm(Film film) {
        Film nouveauFilm = null;
        return nouveauFilm;
    }

    public Film modifierFilm(Long id, Film filmModifie) {
        Film filmAModifier = null;
        return filmAModifier;
    }

    public Film trouverFilm(Long id) {
        Film filmATrouver = null;
        return filmATrouver;
    }

    public List<Film> trouverTousLesFilms() {
        List<Film> films = null;
        return films;
    }

    public boolean supprimerFilm(Long id) {
        boolean estSupprime = false;
        return estSupprime;
    }

    public List<Film> trouverFilmsParGenre(String genre) {
        List<Film> filmsParGenre = null;
        return filmsParGenre;
    }


}
