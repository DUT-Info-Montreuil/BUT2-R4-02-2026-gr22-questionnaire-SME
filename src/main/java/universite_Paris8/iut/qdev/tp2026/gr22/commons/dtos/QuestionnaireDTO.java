package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.Langue;

import java.util.List;

public class QuestionnaireDTO {

    private int id;
    private String libelle;
    private List<QuestionDTO> questions;
    private Langue langue;
    private int nb_question;

    public QuestionnaireDTO(int id, String libelle, List<QuestionDTO> questions, Langue langue) {
        this.id = id;
        this.libelle = libelle;
        this.questions = questions;
        this.langue = langue;
        this.nb_question = (questions != null) ? questions.size() : 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
        this.nb_question = (questions != null) ? questions.size() : 0;
    }

    public Langue getLangue() { return langue; }
    public void setLangue(Langue langue) { this.langue = langue; }

    public int getNb_question() { return nb_question; }

    @Override
    public String toString() {
        return "QuestionnaireDTO{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", langue=" + langue +
                ", nb_question=" + nb_question +
                '}';
    }
}