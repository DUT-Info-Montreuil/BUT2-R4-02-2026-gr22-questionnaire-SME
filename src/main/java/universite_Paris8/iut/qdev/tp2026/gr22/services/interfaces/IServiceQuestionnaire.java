package universite_Paris8.iut.qdev.tp2026.gr22.services.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;

import java.util.List;

public interface IServiceQuestionnaire {

    /**
     * Charge un fichier depuis le chemin donné et retourne son contenu ligne par ligne.
     *
     * @param cheminFichier chemin vers le fichier à charger
     * @return liste des lignes du fichier
     * @throws ExceptionNotFound          si l'emplacement est introuvable
     * @throws ExceptionFichierNotExist   si le fichier n'existe pas
     * @throws ExceptionFichierEmpty      si le fichier est vide
     * @throws ExceptionRecupFail         si la récupération des données échoue
     */
    List<String> chargerFichier(String cheminFichier)
            throws ExceptionNotFound, ExceptionFichierNotExist, ExceptionFichierEmpty, ExceptionRecupFail;

    /**
     * Parse une ligne de texte et retourne un QuestionDTO.
     *
     * @param ligne ligne brute à parser
     * @return QuestionDTO construit depuis la ligne
     * @throws ExceptionQuestionInvalid si la ligne est mal formatée
     * @throws ExceptionRecupFail       si la récupération échoue
     */
    QuestionDTO chargerQuestion(String ligne)
            throws ExceptionQuestionInvalid, ExceptionRecupFail;

    /**
     * Retourne la question courante du questionnaire chargé.
     *
     * @return QuestionDTO courant
     * @throws ExceptionNotFound si aucune question n'est disponible
     */
    QuestionDTO getQuestion() throws ExceptionNotFound;
}