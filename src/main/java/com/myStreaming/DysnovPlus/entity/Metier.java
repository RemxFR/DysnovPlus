package com.myStreaming.DysnovPlus.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_metier")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Metier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "metier", unique = true)
    private String metier;

    @ManyToMany(mappedBy = "metiers")
    private List<Personne> personnes;
}
