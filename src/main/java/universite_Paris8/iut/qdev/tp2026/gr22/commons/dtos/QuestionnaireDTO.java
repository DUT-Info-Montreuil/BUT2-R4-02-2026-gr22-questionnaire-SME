package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.LangueEnum;

import java.util.List;

public class QuestionnaireDTO {

    private int id;
    private String libelle;
    private List<QuestionDTO> questions;
    private LangueEnum langue;
    private int nb_question;

    public QuestionnaireDTO(int id, String libelle, List<QuestionDTO> questions, LangueEnum langue) {
        this.id = id;
        this.libelle = libelle;
        this.questions = questions;
        this.langue = langue;
        this.nb_question = (questions != null) ? questions.size() : 0;
    }

    public String getLibelle() {
        return libelle;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
        this.nb_question = (questions != null) ? questions.size() : 0;
    }

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