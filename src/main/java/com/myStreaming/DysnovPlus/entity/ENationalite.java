package com.myStreaming.DysnovPlus.entity;

public enum ENationalite {

    FRANCE("Française"),
    UK("Anglaise"),
    ESPAGNE("Espagnole"),
    ITALIE("Italienne"),
    ALLEMAGNE("Allemande"),
    USA("Etats-Unienne");

    private String nationalite;

    ENationalite(String nationalite) {
        this.nationalite = nationalite;
    }
}
