package universite_Paris8.iut.qdev.tp2026.gr22.services.impls;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.LangueEnum;
import universite_Paris8.iut.qdev.tp2026.gr22.services.interfaces.IServiceQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceQuestionnaireImpl implements IServiceQuestionnaire {

    private static final String SEPARATEUR = ";";
    private static final int NB_CHAMPS = 8;

    private List<QuestionDTO> questions;
    private int indexCourant;

    public ServiceQuestionnaireImpl() {
        this.questions = new ArrayList<>();
        this.indexCourant = 0;
    }

    // -------------------------------------------------------
    // chargerFichier()
    // -------------------------------------------------------

    @Override
    public List<String> chargerFichier(String cheminFichier)
            throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException {
        validerChemin(cheminFichier);
        File fichier = getFichierValide(cheminFichier);
        List<String> lignes = lireLignes(fichier);
        validerContenu(lignes, cheminFichier);
        chargerQuestionsDepuisLignes(lignes);
        return lignes;
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

    // ignore les lignes qui sont vides et mettre dans une liste
    private List<String> lireLignes(File fichier) throws RecupFailException {
        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (!ligne.trim().isEmpty()) lignes.add(ligne);
            }
        } catch (IOException e) {
            throw new RecupFailException("Erreur de lecture : " + e.getMessage());
        }
        return lignes;
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

    // essaie de créer une question
    private void ajouterQuestionSiValide(String ligne) {
        try {
            this.questions.add(chargerQuestion(ligne));
        } catch (QuestionInvalidException | RecupFailException e) {
            System.err.println("Ligne ignorée (format invalide) : " + ligne);
        }
    }

    // Elle transforme une ligne du fichier en objet QuestionDTO
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
            int id             = parseId(parts[0]);
            String libelle     = parts[1].trim();
            String theme       = parts[2].trim();
            DifficulteEnum diff    = parseDifficulte(parts[3]);
            String reponse     = parts[4].trim();
            String explication = parts[5].trim();
            String reference   = parts[6].trim();
            LangueEnum langue      = parseLangue(parts[7]);
            return new QuestionDTO(id, libelle, theme, diff, reponse, explication, reference, langue);
        } catch (QuestionInvalidException e) {
            throw e;
        } catch (Exception e) {
            throw new RecupFailException("Erreur inattendue : " + e.getMessage());
        }
    }

    // Convertit une chaîne en int
    private int parseId(String valeur) throws QuestionInvalidException {
        try {
            return Integer.parseInt(valeur.trim());
        } catch (NumberFormatException e) {
            throw new QuestionInvalidException("ID non entier : " + valeur);
        }
    }

    // Convertit une chaîne en enum Difficulte
    private DifficulteEnum parseDifficulte(String valeur) throws QuestionInvalidException {
        try {
            return DifficulteEnum.valueOf(valeur.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new QuestionInvalidException("Difficulté invalide : " + valeur);
        }
    }

    // Convertit une chaîne en enum Langue
    private LangueEnum parseLangue(String valeur) throws QuestionInvalidException {
        try {
            return LangueEnum.valueOf(valeur.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new QuestionInvalidException("Langue invalide : " + valeur);
        }
    }

    // -------------------------------------------------------
    // fournirQuestionnaire(String cheminFichier)
    // -------------------------------------------------------
    @Override
    public QuestionnaireDTO fournirQuestionnaire(String cheminFichier) throws NotFoundException, FichierNotExistException, FichierEmptyException, RecupFailException {
        return null;
    }

    // -------------------------------------------------------
    // getQuestion()
    // -------------------------------------------------------

    @Override
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

    public List<QuestionDTO> getQuestions(){
        return questions;
    }
    public int getNbQuestions(){
        return (questions != null) ? questions.size() : 0;
    }
    public void resetIndex(){
        this.indexCourant = 0;
    }
}