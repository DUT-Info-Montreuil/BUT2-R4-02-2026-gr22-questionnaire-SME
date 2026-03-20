package universite_Paris8.iut.qdev.tp2026.gr22.services.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;

import java.util.List;

public interface IServiceQuestionnaire {

    /**
     * Charge un fichier depuis le chemin donné et retourne son contenu ligne par ligne.
     *
     * @param cheminFichier chemin vers le fichier à charger
     * @return liste des lignes du fichier
     * @throws NotFoundException          si l'emplacement est introuvable
     * @throws FichierNotExistException   si le fichier n'existe pas
     * @throws FichierEmptyException      si le fichier est vide
     * @throws RecupFailException         si la récupération des données échoue
     */
    List<String> chargerFichier(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException;

    /**
     * Retourne le questionnaire chargé en mémoire.
     * Si aucun questionnaire n'est encore chargé, le charge depuis le fichier.
     *
     * @param cheminFichier chemin vers le fichier source
     * @return QuestionnaireDTO chargé
     */
    QuestionnaireDTO fournirQuestionnaire(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException;

    /**
     * Retourne la question courante du questionnaire chargé.
     *
     * @return QuestionDTO courant
     * @throws NotFoundException si aucune question n'est disponible
     */
    QuestionDTO getQuestion() throws NotFoundException;
}