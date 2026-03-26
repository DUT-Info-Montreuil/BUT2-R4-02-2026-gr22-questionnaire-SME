package universite_Paris8.iut.qdev.tp2026.gr22.services;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;
import java.io.File;
import java.util.List;

public class QuestionnaireService {

    private final IFileHandler fileHandler;

    public QuestionnaireService(IFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public List<QuestionnaireDTO> chargerQuestionnaires(String cheminDossier, String nomFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException {

        File dossier = new File(cheminDossier);
        if (!dossier.exists() || !dossier.isDirectory()) {
            throw new NotFoundException();
        }

        File fichier = new File(dossier, nomFichier);
        if (!fichier.exists()) {
            throw new FichierNotExistException();
        }

        if (fichier.length() == 0) {
            throw new FichierEmptyException();
        }

        try {
            List<QuestionnaireDTO> questionnaires = fileHandler.extraireDonnees(fichier);
            if (questionnaires == null || questionnaires.isEmpty()) {
                throw new RecupFailException();
            }
            return questionnaires;
        } catch (Exception e) {
            throw new RecupFailException();
        }
    }
}