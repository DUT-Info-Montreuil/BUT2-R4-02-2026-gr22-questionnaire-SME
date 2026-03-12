package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.Difficulte;

/**
 * DTO représentant la difficulté d'une question avec son système de points
 */
public class DifficulteDTO {

    private Difficulte difficulte;  // SIMPLE, INTERMEDIAIRE ou EXPERT
    private int nbPoints;

    // Constructeur par défaut
    public DifficulteDTO() {
    }

    // Constructeur avec paramètres (enum)
    public DifficulteDTO(Difficulte difficulte, int nbPoints) {
        this.difficulte = difficulte;
        this.nbPoints = nbPoints;
    }

    // Constructeur avec paramètres (int pour compatibilité)
    public DifficulteDTO(int niveauDifficulte, int nbPoints) {
        this.difficulte = Difficulte.fromNiveau(niveauDifficulte);
        this.nbPoints = nbPoints;
    }

    // Getters et Setters
    public Difficulte getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(Difficulte difficulte) {
        this.difficulte = difficulte;
    }

    public void setDifficulte(int niveauDifficulte) {
        this.difficulte = Difficulte.fromNiveau(niveauDifficulte);
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    // Méthode utilitaire pour obtenir le niveau (1, 2 ou 3)
    public int getNiveau() {
        return difficulte != null ? difficulte.getNiveau() : 0;
    }

    // Méthode utilitaire pour obtenir le libellé
    public String getLibelleDifficulte() {
        return difficulte != null ? difficulte.getLibelle() : "Inconnu";
    }

    @Override
    public String toString() {
        return "DifficulteDTO{" +
                "difficulte=" + difficulte +
                ", nbPoints=" + nbPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DifficulteDTO that = (DifficulteDTO) o;
        return nbPoints == that.nbPoints && difficulte == that.difficulte;
    }

    @Override
    public int hashCode() {
        int result = difficulte != null ? difficulte.hashCode() : 0;
        result = 31 * result + nbPoints;
        return result;
    }
}