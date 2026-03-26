package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.LangueEnum;

public class QuestionDTO {

    private final int id;
    private final String libelle;
    private final String theme;
    private final DifficulteDTO difficulte;
    private final String reponse;
    private final String explication;
    private final String reference;
    private final LangueEnum langue;

    public QuestionDTO(int id, String libelle, String theme, DifficulteDTO difficulte,
                       String reponse, String explication, String reference, LangueEnum langue) {
        this.id = id;
        this.libelle = libelle;
        this.theme = theme;
        this.difficulte = difficulte;
        this.reponse = reponse;
        this.explication = explication;
        this.reference = reference;
        this.langue = langue;
    }

    public int getId(){
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public DifficulteDTO getDifficulte(){
        return difficulte;
    }

    public String getReponse(){
        return reponse;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", libelle='" + libelle + "'" +
                ", theme='" + theme + "'" +
                ", difficulte=" + difficulte +
                ", reponse='" + reponse + "'" +
                ", explication='" + explication + "'" +
                ", reference='" + reference + "'" +
                ", langue=" + langue +
                '}';
    }
}