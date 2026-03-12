package universite_Paris8.iut.qdev.tp2026.gr22.commons.enums;

public enum Difficulte {
    FACILE("Facile", 1),
    MOYEN("Moyen", 2),
    DIFFICILE("Difficile", 3);

    private final String libelle;
    private final int points;

    Difficulte(String libelle, int points) {
        this.libelle = libelle;
        this.points = points;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getPoints() {
        return points;
    }
}