package universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO représentant un questionnaire complet
 */
public class QuestionnaireDTO {

    private List<QuestionDTO> questions;
    private List<String> langues;
    private int nbQuestions;

    // Constructeur par défaut
    public QuestionnaireDTO() {
        this.questions = new ArrayList<>();
        this.langues = new ArrayList<>();
        this.nbQuestions = 0;
    }

    // Constructeur avec paramètres
    public QuestionnaireDTO(List<QuestionDTO> questions, List<String> langues, int nbQuestions) {
        this.questions = questions != null ? questions : new ArrayList<>();
        this.langues = langues != null ? langues : new ArrayList<>();
        this.nbQuestions = nbQuestions;
    }

    // Getters et Setters
    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
        // Mettre à jour automatiquement le nombre de questions
        if (questions != null) {
            this.nbQuestions = questions.size();
        }
    }

    public void ajouterQuestion(QuestionDTO question) {
        if (this.questions == null) {
            this.questions = new ArrayList<>();
        }
        this.questions.add(question);
        this.nbQuestions = this.questions.size();
    }

    public void retirerQuestion(QuestionDTO question) {
        if (this.questions != null) {
            this.questions.remove(question);
            this.nbQuestions = this.questions.size();
        }
    }

    public List<String> getLangues() {
        return langues;
    }

    public void setLangues(List<String> langues) {
        this.langues = langues;
    }

    public void ajouterLangue(String langue) {
        if (this.langues == null) {
            this.langues = new ArrayList<>();
        }
        if (!this.langues.contains(langue)) {
            this.langues.add(langue);
        }
    }

    public int getNbQuestions() {
        return nbQuestions;
    }

    public void setNbQuestions(int nbQuestions) {
        this.nbQuestions = nbQuestions;
    }

    /**
     * Calcule le score total possible du questionnaire
     */
    public int calculerScoreMaximal() {
        if (questions == null || questions.isEmpty()) {
            return 0;
        }

        int scoreMax = 0;
        for (QuestionDTO question : questions) {
            if (question.getDifficulte() != null) {
                scoreMax += question.getDifficulte().getNbPoints();
            }
        }
        return scoreMax;
    }

    /**
     * Récupère les questions par thème
     */
    public List<QuestionDTO> getQuestionsParTheme(String theme) {
        List<QuestionDTO> questionsTheme = new ArrayList<>();
        if (questions != null) {
            for (QuestionDTO question : questions) {
                if (question.getTheme() != null && question.getTheme().equalsIgnoreCase(theme)) {
                    questionsTheme.add(question);
                }
            }
        }
        return questionsTheme;
    }

    /**
     * Récupère les questions par difficulté
     */
    public List<QuestionDTO> getQuestionsParDifficulte(int difficulte) {
        List<QuestionDTO> questionsDiff = new ArrayList<>();
        if (questions != null) {
            for (QuestionDTO question : questions) {
                if (question.getDifficulte() != null &&
                        question.getDifficulte().getDifficulte() == difficulte) {
                    questionsDiff.add(question);
                }
            }
        }
        return questionsDiff;
    }

    @Override
    public String toString() {
        return "QuestionnaireDTO{" +
                "nbQuestions=" + nbQuestions +
                ", langues=" + langues +
                ", scoreMaximal=" + calculerScoreMaximal() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionnaireDTO that = (QuestionnaireDTO) o;
        if (nbQuestions != that.nbQuestions) return false;
        if (questions != null ? !questions.equals(that.questions) : that.questions != null)
            return false;
        return langues != null ? langues.equals(that.langues) : that.langues == null;
    }

    @Override
    public int hashCode() {
        int result = questions != null ? questions.hashCode() : 0;
        result = 31 * result + (langues != null ? langues.hashCode() : 0);
        result = 31 * result + nbQuestions;
        return result;
    }
}