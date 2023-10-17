package com.myStreaming.DysnovPlus.entity;

public enum EGenreFilm {

    ACTION("Action"),
    ANIMATION("Animation"),
    AVENTURE("Aventure"),
    ANTICIPATION("Anticipation"),
    CATASTROPHE("Catastrophe"),
    CAPE_ET_EPEE("De cape et d'épée"),
    CHRETIEN("Chrétien"),
    COMEDIE("Comédie"),
    COMEDIE_MUSICALE("Comédie musicale"),
    DRAME("Drame"),
    FANTASTIQUE("Fantastique"),
    FANTASY("Fantasy"),
    HORREUR("Horreur"),
    HISTORIQUE("Historique"),
    BIOPIC("Biographique"),
    PEPLUM("Péplum"),
    ROMANCE("Romance"),
    SCIFI("Science-Fiction"),
    SPIRITUEL("Spirituel"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private String genre;

    EGenreFilm(String genre) {
        this.genre = genre;
    }
}
