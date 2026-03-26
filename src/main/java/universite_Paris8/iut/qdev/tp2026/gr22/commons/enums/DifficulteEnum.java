package universite_Paris8.iut.qdev.tp2026.gr22.commons.enums;

public enum DifficulteEnum {

    FACILE("Facile", 1),
    MOYEN("Moyen", 2),
    DIFFICILE("Difficile", 3);

    private final String libelle;
    private final int niveau;

    DifficulteEnum(String libelle, int niveau) {
        this.libelle = libelle;
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public static DifficulteEnum fromNiveau(int niveau) {
        for (DifficulteEnum d : values()) {
            if (d.niveau == niveau) return d;
        }
        throw new IllegalArgumentException("Niveau de difficulté inconnu : " + niveau);
    }
}
