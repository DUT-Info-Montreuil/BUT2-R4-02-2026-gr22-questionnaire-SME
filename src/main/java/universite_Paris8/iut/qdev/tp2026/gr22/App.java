package universite_Paris8.iut.qdev.tp2026.gr22;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.services.impls.ServiceQuestionnaireImpl;
import universite_Paris8.iut.qdev.tp2026.gr22.services.interfaces.IServiceQuestionnaire;

public class App {

    public static void main(String[] args) {
        IServiceQuestionnaire service = new ServiceQuestionnaireImpl();
        String chemin = "src/main/resources/questionsQuizz_2025_V1.csv";

        try {
            QuestionnaireDTO questionnaire = service.fournirQuestionnaire(chemin);
            System.out.println("Questionnaire chargé : " + questionnaire.getLibelle());
            System.out.println("Nombre de questions  : " + questionnaire.getNb_question());
            System.out.println("---");

            for (int i = 0; i < 20; i++) {
                QuestionDTO q = service.getQuestion();
                System.out.println("Q" + q.getId() + " [" + q.getDifficulte() + "] " + q.getLibelle());
                System.out.println("  Réponse : " + q.getReponse());
                System.out.println("\n");
            }

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}