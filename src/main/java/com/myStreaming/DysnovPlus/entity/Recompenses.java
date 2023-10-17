package com.myStreaming.DysnovPlus.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "t_recompenses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recompenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "recompense", unique = true)
    private String recompense;

    @JsonIgnore
    @ManyToMany(mappedBy = "recompenses")
    private List<Personne> personnes;
}
