package com.myStreaming.DysnovPlus.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_film")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", length = 128)
    private String titre;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "dateSortie")
    @Temporal(TemporalType.DATE)
    private Date dateDeSortie;

    @ManyToMany
    @JoinTable(name = "personnes_films",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "personne", referencedColumnName = "id"))
    private List<Personne> personnes;

    @Column(name = "genre")
    private String genreFilm;

    @Column(name = "duree")
    private int duree;
}
