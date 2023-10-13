package com.myStreaming.DysnovPlus.entity;

public enum EMetier {

    ACTEUR("Acteur"),
    ACTRICE("Actrice"),
    REALISATEUR("Réalisateur"),
    REALISATRICE("Réalisatrice");

    private String metier;

    EMetier(String metier) {
        this.metier = metier;
    }
}
