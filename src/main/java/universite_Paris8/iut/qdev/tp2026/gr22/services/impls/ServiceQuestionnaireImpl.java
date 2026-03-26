package universite_Paris8.iut.qdev.tp2026.gr22.services.impls;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.DifficulteDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.LangueEnum;
import universite_Paris8.iut.qdev.tp2026.gr22.services.interfaces.IServiceQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServiceQuestionnaireImpl implements IServiceQuestionnaire {

    private static final String SEPARATEUR = ";";
    private static final int NB_CHAMPS = 9;

    private List<QuestionDTO> questions;
    private QuestionnaireDTO questionnaire;
    private int indexCourant;

    public ServiceQuestionnaireImpl() {
        this.questions = new ArrayList<>();
        this.questionnaire = null;
        this.indexCourant = 0;
    }

    // -------------------------------------------------------
    // chargerFichier()
    // -------------------------------------------------------

    public void chargerFichier(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException {
        validerChemin(cheminFichier);
        File fichier = getFichierValide(cheminFichier);
        List<String> lignes = lireLignes(fichier);
        validerContenu(lignes, cheminFichier);
        chargerQuestionsDepuisLignes(lignes);
    }

    private void validerChemin(String chemin) throws NotFoundException {
        if (chemin == null || chemin.trim().isEmpty()) {
            throw new NotFoundException("Le chemin fourni est invalide : " + chemin);
        }
    }

    private File getFichierValide(String chemin) throws FichierNotExistException {
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile()) {
            throw new FichierNotExistException("Fichier introuvable : " + chemin);
        }
        return fichier;
    }

    private List<String> lireLignes(File fichier) throws RecupFailException {
        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(fichier.toPath()), StandardCharsets.UTF_8))) {
            String ligne;
            boolean premiereLigne = true;
            while ((ligne = reader.readLine()) != null) {
                ligne = nettoyerLigne(ligne, premiereLigne);
                premiereLigne = false;
                if (!ligne.isEmpty()) lignes.add(ligne);
            }
        } catch (IOException e) {
            throw new RecupFailException("Erreur de lecture : " + e.getMessage());
        }
        return lignes;
    }

    private String nettoyerLigne(String ligne, boolean premiereLigne) {
        ligne = ligne.trim();
        if (premiereLigne && ligne.startsWith("\uFEFF")) {
            ligne = ligne.substring(1);
        }
        return ligne;
    }

    private void validerContenu(List<String> lignes, String chemin) throws FichierEmptyException {
        if (lignes.isEmpty()) {
            throw new FichierEmptyException("Le fichier est vide : " + chemin);
        }
    }

    private void chargerQuestionsDepuisLignes(List<String> lignes) {
        this.questions = new ArrayList<>();
        this.indexCourant = 0;
        for (String ligne : lignes) {
            ajouterQuestionSiValide(ligne);
        }
    }

    private void ajouterQuestionSiValide(String ligne) {
        try {
            this.questions.add(chargerQuestion(ligne));
        } catch (QuestionInvalidException | RecupFailException e) {
            System.err.println("Ligne ignorée (format invalide) : " + ligne);
        }
    }

    // -------------------------------------------------------
    // chargerQuestion()
    // -------------------------------------------------------

    public QuestionDTO chargerQuestion(String ligne)
            throws QuestionInvalidException, RecupFailException {
        validerLigne(ligne);
        String[] parts = decouper(ligne);
        return construireQuestion(parts);
    }

    private void validerLigne(String ligne) throws QuestionInvalidException {
        if (ligne == null || ligne.trim().isEmpty()) {
            throw new QuestionInvalidException("La ligne est vide ou nulle.");
        }
    }

    private String[] decouper(String ligne) throws QuestionInvalidException {
        String[] parts = ligne.split(SEPARATEUR, -1);
        if (parts.length < NB_CHAMPS) {
            throw new QuestionInvalidException(
                    "Format invalide (attendu " + NB_CHAMPS + " champs) : " + ligne);
        }
        return parts;
    }

    private QuestionDTO construireQuestion(String[] parts)
            throws QuestionInvalidException, RecupFailException {
        try {
            // Format CSV : idQuestionnaire;libelleQuestionnaire;idQuestion;langue;libelle;reponse;difficulte;explication;reference
            int id              = parseId(parts[2]);
            String theme        = parts[1].trim();
            LangueEnum langue   = parseLangue(parts[3]);
            String libelle      = parts[4].trim();
            String reponse      = parts[5].trim();
            DifficulteDTO diff  = parseDifficulte(parts[6]);
            String explication  = parts[7].trim();
            String reference    = parts[8].trim();
            return new QuestionDTO(id, libelle, theme, diff, reponse, explication, reference, langue);
        } catch (QuestionInvalidException e) {
            throw e;
        } catch (Exception e) {
            throw new RecupFailException("Erreur inattendue : " + e.getMessage());
        }
    }

    private int parseId(String valeur) throws QuestionInvalidException {
        try {
            return Integer.parseInt(valeur.trim());
        } catch (NumberFormatException e) {
            throw new QuestionInvalidException("ID non entier : " + valeur);
        }
    }

    private DifficulteDTO parseDifficulte(String valeur) throws QuestionInvalidException {
        try {
            DifficulteEnum niveau = DifficulteEnum.fromNiveau(Integer.parseInt(valeur.trim()));
            return new DifficulteDTO(niveau, niveau.getNiveau());
        } catch (NumberFormatException e) {
            try {
                DifficulteEnum niveau = DifficulteEnum.valueOf(valeur.trim().toUpperCase());
                return new DifficulteDTO(niveau, niveau.getNiveau());
            } catch (IllegalArgumentException ex) {
                throw new QuestionInvalidException("Difficulté invalide : " + valeur);
            }
        } catch (IllegalArgumentException e) {
            throw new QuestionInvalidException("Difficulté invalide : " + valeur);
        }
    }

    private LangueEnum parseLangue(String valeur) throws QuestionInvalidException {
        switch (valeur.trim().toLowerCase()) {
            case "fr":       return LangueEnum.FRANCAIS;
            case "en":       return LangueEnum.ANGLAIS;
            case "francais": return LangueEnum.FRANCAIS;
            case "anglais":  return LangueEnum.ANGLAIS;
            default: throw new QuestionInvalidException("Langue invalide : " + valeur);
        }
    }

    // -------------------------------------------------------
    // getQuestion()
    // -------------------------------------------------------

    public QuestionDTO getQuestion() throws NotFoundException {
        verifierQuestionsDisponibles();
        reinitialiserSiNecessaire();
        return questions.get(indexCourant++);
    }

    private void verifierQuestionsDisponibles() throws NotFoundException {
        if (questions == null || questions.isEmpty()) {
            throw new NotFoundException("Aucune question disponible. Chargez d'abord un fichier.");
        }
    }

    private void reinitialiserSiNecessaire() {
        if (indexCourant >= questions.size()) {
            indexCourant = 0;
        }
    }

    // -------------------------------------------------------
    // fournirQuestionnaire()
    // -------------------------------------------------------

    public QuestionnaireDTO fournirQuestionnaire(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException {
        if (questionnaireEstDisponible()) return questionnaire;
        return chargerEtRetournerQuestionnaire(cheminFichier);
    }

    private boolean questionnaireEstDisponible() {
        return questionnaire != null;
    }

    private QuestionnaireDTO chargerEtRetournerQuestionnaire(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException {
        chargerFichier(cheminFichier);
        questionnaire = new QuestionnaireDTO(1, cheminFichier, questions, null);
        return questionnaire;
    }
}