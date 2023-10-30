package com.myStreaming.DysnovPlus.dao.service;

import com.myStreaming.DysnovPlus.dao.repository.IFilmRepo;
import com.myStreaming.DysnovPlus.entity.Film;
import com.myStreaming.DysnovPlus.entity.Personne;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.query.spi.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FilmService {

    private IFilmRepo filmRepo;
    private ActeurService acteurService;
    private RealisateurService realisateurService;
    @PersistenceContext
    private EntityManager entityManager;

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
        List<Film> filmsParPersonne = null;
        try {
            String queryStrg = "select f.id, f.titre, f.description, f.date_sortie, f.duree, f.genre from mydysnovplus.t_film f join mydysnovplus.personnes_films pf join mydysnovplus.t_personne tp on f.id = pf.film_id and tp.id = pf.personne where tp.nom = ?1";
            Query query = entityManager.createNativeQuery(queryStrg);
            query.setParameter(1, nom);
            filmsParPersonne = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmsParPersonne;
    }

    public List<Film> trouverFilmParPersonnes(List<Personne> nomsPersonnes) {
        List<Film> filmsParActeur = null;
        List<String> andList = new ArrayList<>();
        try {

            for (int i = 0; i < nomsPersonnes.size(); i++) {
                String and = "";
                if (i < nomsPersonnes.size() - 1) {
                    and = "tp.nom = \'" + nomsPersonnes.get(i).getNom() + "\' and " + "tp.prenom = \'" + nomsPersonnes.get(i).getPrenom() + "\'" + " and ";
                } else {
                    and = "tp.nom = \'" + nomsPersonnes.get(i).getNom()+ "\' and " + "tp.prenom = \'" + nomsPersonnes.get(i).getPrenom() + "\'";
                }
                andList.add(and);
                System.out.println(and);
            }

            Iterator<String> it = andList.iterator();
            String andQueryStrg = "";
            while (it.hasNext()) {
                    andQueryStrg = andQueryStrg + it.next();
            }
            System.out.println(andQueryStrg);
            String queryStrg = "select f.id, f.titre, f.description, f.date_sortie, f.duree, f.genre from mydysnovplus.t_film f join mydysnovplus.personnes_films pf join mydysnovplus.t_personne tp on f.id = pf.film_id and tp.id = pf.personne where " + andQueryStrg;

            System.out.println(queryStrg);
            Query query = entityManager.createNativeQuery(queryStrg);
            filmsParActeur = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmsParActeur;
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
            if (filmAModifier != null && personne != null) {
                filmAModifier.getPersonnes().add(personne);
                this.filmRepo.save(filmAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }

    public Film ajouterActeurAuFilmParId(Long filmId, Long acteurId) {
        Film filmAModifier = null;
        Personne acteurExistant = null;
        try {
            acteurExistant = this.acteurService.trouverActeur(acteurId);
            filmAModifier = this.trouverFilm(filmId);
            if (filmAModifier != null && acteurExistant != null) {
                filmAModifier.getPersonnes().add(acteurExistant);
                this.filmRepo.save(filmAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }

    public Film ajouterRealisateurAuFilmParId(Long filmId, Long realId) {
        Film filmAModifier = null;
        Personne realisateurExistant = null;
        try {
            realisateurExistant = this.realisateurService.trouverRealisateur(realId);
            filmAModifier = this.trouverFilm(filmId);
            if (filmAModifier != null && realisateurExistant != null) {
                filmAModifier.getPersonnes().add(realisateurExistant);
                this.filmRepo.save(filmAModifier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmAModifier;
    }

}
