package universite_Paris8.iut.qdev.tp2026.gr22.commons.enums;

public enum Difficulte {

    FACILE("Facile", 1),
    MOYEN("Moyen", 2),
    DIFFICILE("Difficile", 3);

    private final String libelle;
    private final int niveau;

    Difficulte(String libelle, int niveau) {
        this.libelle = libelle;
        this.niveau = niveau;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getNiveau() {
        return niveau;
    }

    /**
     * Retourne le Difficulte correspondant à un niveau entier.
     * @throws IllegalArgumentException si le niveau n'existe pas
     */
    public static Difficulte fromNiveau(int niveau) {
        for (Difficulte d : values()) {
            if (d.niveau == niveau) return d;
        }
        throw new IllegalArgumentException("Niveau de difficulté inconnu : " + niveau);
    }
}