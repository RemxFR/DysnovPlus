package com.myStreaming.DysnovPlus.dao.repository;

import com.myStreaming.DysnovPlus.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFilmRepo extends JpaRepository<Film, Long> {

    @Query("select f from Film f where f.genreFilm = ?1")
    List<Film> trouverFilmsParGenre(String genre);
}
