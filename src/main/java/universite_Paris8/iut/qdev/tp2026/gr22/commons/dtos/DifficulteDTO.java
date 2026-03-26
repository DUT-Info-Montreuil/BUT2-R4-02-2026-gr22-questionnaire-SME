package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.DifficulteEnum;

/**
 * DTO représentant la difficulté d'une question avec son système de points
 */
public class DifficulteDTO {

    private final DifficulteEnum difficulte;  // SIMPLE, INTERMEDIAIRE ou EXPERT
    private final int nbPoints;

    // Constructeur avec paramètres (enum)
    public DifficulteDTO(DifficulteEnum difficulte, int nbPoints) {
        this.difficulte = difficulte;
        this.nbPoints = nbPoints;
    }

    // Constructeur avec paramètres (int pour compatibilité)
    public DifficulteDTO(int niveauDifficulte, int nbPoints) {
        this.difficulte = DifficulteEnum.fromNiveau(niveauDifficulte);
        this.nbPoints = nbPoints;
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