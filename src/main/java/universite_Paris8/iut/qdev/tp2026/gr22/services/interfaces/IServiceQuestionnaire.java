package universite_Paris8.iut.qdev.tp2026.gr22.services.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;

public interface IServiceQuestionnaire {

    /**
     * Nom       : chargerFichier()
     * Retourne  : List<String>
     * Paramètre : String cheminFichier
     * Exceptions: NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException
     * Signature : public List<String> chargerFichier(String cheminFichier)
     */
    void chargerFichier(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException;

    /**
     * Nom       : fournirQuestionnaire()
     * Retourne  : QuestionnaireDTO
     * Paramètre : String cheminFichier
     * Exceptions: NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException
     * Signature : public QuestionnaireDTO fournirQuestionnaire(String cheminFichier)
     */
    QuestionnaireDTO fournirQuestionnaire(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException;

    QuestionDTO getQuestion() throws NotFoundException;
}