package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

/**
 * Énumération représentant les niveaux de difficulté
 */
public enum Difficulte {
    SIMPLE(1, "Simple"),
    INTERMEDIAIRE(2, "Intermédiaire"),
    EXPERT(3, "Expert");

    private final int niveau;
    private final String libelle;

    Difficulte(int niveau, String libelle) {
        this.niveau = niveau;
        this.libelle = libelle;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getLibelle() {
        return libelle;
    }

    /**
     * Récupère la difficulté à partir du niveau
     */
    public static Difficulte fromNiveau(int niveau) {
        for (Difficulte d : Difficulte.values()) {
            if (d.niveau == niveau) {
                return d;
            }
        }
        throw new IllegalArgumentException("Niveau de difficulté invalide : " + niveau);
    }

    @Override
    public String toString() {
        return libelle + " (niveau " + niveau + ")";
    }
}