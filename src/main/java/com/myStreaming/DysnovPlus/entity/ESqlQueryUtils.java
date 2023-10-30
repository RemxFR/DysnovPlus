package com.myStreaming.DysnovPlus.entity;

public enum ESqlQueryUtils {

    ROW_LIMIT_50(50),
    ROW_LIMIT_LABEL("limit "),
    FIND_PERSON_QUERY("select p.id, p.nom, p.prenom, p.nationalite, p.age, p.date_naissance, p.genre," +
            " tm.metier as 'métier' from mydysnovplus.t_personne p join mydysnovplus.t_metier tm right join" +
            " mydysnovplus.t_personnes_métiers m on p.id = m.personne_id and m.metier = tm.id "),
    ACTEUR_QUERY("where tm.metier = 'Actrice' or tm.metier = 'Acteur' "),
    REAL_QUERY("where tm.metier = 'Réalisatrice' or tm.metier = 'Réalisateur' "),
    NOM_QUERY("and p.nom = ?1 "),
    NOM_PRENOM_QUERY("and p.nom = ?1 and p.pernom = ?2 ");
    private int rowLimit;
    private String label;

    ESqlQueryUtils(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    ESqlQueryUtils(String limitLabel) {
        this.label = limitLabel;
    }

    public String getLabel() {
        return label;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public static int getRowLimit(Integer rowLimit) {
        if(rowLimit != null && rowLimit > 20 || rowLimit == null) {
            rowLimit = ROW_LIMIT_50.getRowLimit();
        }
        return rowLimit;
    }
}
