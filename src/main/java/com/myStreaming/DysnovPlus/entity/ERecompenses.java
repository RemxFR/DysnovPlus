package com.myStreaming.DysnovPlus.entity;

public enum ERecompenses {
    CESAR_FILM("César du Meilleur Film"),
    CESAR_REAL("César du Meilleur Réalisateur"),
    CESAR_ACTEUR("César du Meilleur Acteur"),
    CESAR_ACTRICE("César de la Meilleure Actrice"),
    CESAR_SEC_ROLE_M("César du Meilleur Acteur dans un second rôle"),
    CESAR_SEC_ROLE_F("César de la Meilleure Actrice dans un second rôle"),
    CESAR_ESPOIR_M("César du Meilleur Espoir Masculin"),
    CESAR_ESPOIR_F("César du Meilleur Espoir Féminin"),
    CESAR_SCENAR("César du Meilleur Scénario Original"),
    CESAR_ADAPT("César de la Meilleure Adaptation"),
    CESAR_DECORS("César des Meilleurs Décors"),
    CESAR_PHOTO("César de la Meilleure Photographie"),
    CESAR_COSTUMES("César des Meilleurs Costumes"),
    CESAR_MONTAGE("César du Meilleur Montage"),
    CESAR_SON("César du Meilleur Son"),
    CESAR_FX("César des Meilleurs Effets Visuels"),
    CESAR_MUSIQUE("César de la Meilleure Musique Originale"),
    CESAR_PREMIER_FILM("César du Meilleur Premier Film"),
    CESAR_ANIM("César du Meilleur Film d'Animation"),
    CESAR_DOCU("César du Meilleur Documentaire"),
    CESAR_FILM_ETRANGER("César du Meilleur Film Etranger"),

    OSCAR_FILM("Oscar du Meilleur Film"),
    OSCAR_REAL("Oscar du Meilleur Réalisateur"),
    OSCAR_ACTEUR("Oscar du Meilleur Acteur"),
    OSCAR_ACTRICE("Oscar de la Meilleure Actrice"),
    OSCAR_SEC_ROLE_M("Oscar du Meilleur Acteur dans un second rôle"),
    OSCAR_SEC_ROLE_F("Oscar de la Meilleure Actrice dans un second rôle"),
    OSCAR_ESPOIR_M("Oscar du Meilleur Espoir Masculin"),
    OSCAR_ESPOIR_F("Oscar du Meilleur Espoir Féminin"),
    OSCAR_SCENAR("Oscar du Meilleur Scénario Original"),
    OSCAR_ADAPT("Oscar de la Meilleure Adaptation"),
    OSCAR_DECORS("Oscar des Meilleurs Décors"),
    OSCAR_PHOTO("Oscar de la Meilleure Photographie"),
    OSCAR_COSTUMES("Oscar des Meilleurs Costumes"),
    OSCAR_MONTAGE("Oscar du Meilleur Montage"),
    OSCAR_SON("Oscar du Meilleur Son"),
    OSCAR_FX("Oscar des Meilleurs Effets Visuels"),
    OSCAR_MUSIQUE("Oscar de la Meilleure Musique Originale"),
    OSCAR_PREMIER_FILM("Oscar du Meilleur Premier Film"),
    OSCAR_ANIM("Oscar du Meilleur Film d'Animation"),
    OSCAR_DOCU("Oscar du Meilleur Documentaire"),
    OSCAR_FILM_INTER("Oscar du Meilleur Film International"),
    ;


    private String recompense;

    ERecompenses(String recompense) {
        this.recompense = recompense;
    }
}
