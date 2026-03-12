package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

/**
 * DTO représentant une question du questionnaire
 */
public class QuestionDTO {

    private int id;
    private String libelle;
    private String theme;
    private DifficulteDTO difficulte;
    private String reponse;  // La réponse
    private String explicationReponse;
    private String reference;

    // Constructeur par défaut
    public QuestionDTO() {
    }

    // Constructeur avec paramètres essentiels
    public QuestionDTO(int id, String libelle, String theme) {
        this.id = id;
        this.libelle = libelle;
        this.theme = theme;
    }

    // Constructeur complet
    public QuestionDTO(int id, String libelle, String theme, DifficulteDTO difficulte,
                       String reponse, String explicationReponse, String reference) {
        this.id = id;
        this.libelle = libelle;
        this.theme = theme;
        this.difficulte = difficulte;
        this.reponse = reponse;
        this.explicationReponse = explicationReponse;
        this.reference = reference;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public DifficulteDTO getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(DifficulteDTO difficulte) {
        this.difficulte = difficulte;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getExplicationReponse() {
        return explicationReponse;
    }

    public void setExplicationReponse(String explicationReponse) {
        this.explicationReponse = explicationReponse;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", theme='" + theme + '\'' +
                ", difficulte=" + difficulte +
                ", reponse='" + reponse + '\'' +
                ", explicationReponse='" + explicationReponse + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}