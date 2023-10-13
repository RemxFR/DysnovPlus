package com.myStreaming.DysnovPlus.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "t_personne")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", length = 45)
    private String nom;

    @Column(name = "prenom", length = 45)
    private String prenom;

    @Column(name = "age")
    private int age;

    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "genre")
    private String genrePersonne;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_personnes_métiers",
            joinColumns = @JoinColumn(name = "personne_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "metier", referencedColumnName = "id"))
    private List<Metier> metiers;

    @ManyToMany(mappedBy = "personnes")
    private List<Film> filmographie;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_personnes_récompenses",
            joinColumns = @JoinColumn(name = "personne_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recompense_id", referencedColumnName = "id"))
    private List<Recompenses> recompenses;


}
