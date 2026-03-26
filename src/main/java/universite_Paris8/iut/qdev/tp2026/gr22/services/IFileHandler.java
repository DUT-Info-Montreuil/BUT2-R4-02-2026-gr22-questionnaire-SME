package universite_Paris8.iut.qdev.tp2026.gr22.services;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import java.io.File;
import java.util.List;

public interface IFileHandler {
    /**
     * Lit le fichier et transforme son contenu en liste de DTOs.
     * @throws Exception si le format est invalide ou la lecture impossible.
     */
    List<QuestionnaireDTO> extraireDonnees(File fichier) throws Exception;
}