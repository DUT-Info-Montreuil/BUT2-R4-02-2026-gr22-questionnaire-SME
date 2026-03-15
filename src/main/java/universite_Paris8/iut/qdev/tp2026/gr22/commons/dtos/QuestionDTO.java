package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.Langue;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.Difficulte;

public class QuestionDTO {

    private int id;
    private String libelle;
    private String theme;
    private Difficulte difficulte;
    private String reponse;
    private String explication;
    private String reference;
    private Langue langue;

    public QuestionDTO(int id, String libelle, String theme, Difficulte difficulte,
                       String reponse, String explication, String reference, Langue langue) {
        this.id = id;
        this.libelle = libelle;
        this.theme = theme;
        this.difficulte = difficulte;
        this.reponse = reponse;
        this.explication = explication;
        this.reference = reference;
        this.langue = langue;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public Difficulte getDifficulte() { return difficulte; }
    public void setDifficulte(Difficulte difficulte) { this.difficulte = difficulte; }

    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }

    public String getExplication() { return explication; }
    public void setExplication(String explication) { this.explication = explication; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public Langue getLangue() { return langue; }
    public void setLangue(Langue langue) { this.langue = langue; }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", theme='" + theme + '\'' +
                ", difficulte=" + difficulte +
                ", reponse='" + reponse + '\'' +
                ", explication='" + explication + '\'' +
                ", reference='" + reference + '\'' +
                ", langue=" + langue +
                '}';
    }
}