package universite_Paris8.iut.qdev.tp2026.gr22.services.impls;

import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.Difficulte;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.enums.Langue;
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
            throws ExceptionNotFound, ExceptionFichierNotExist, ExceptionFichierEmpty, ExceptionRecupFail {
        validerChemin(cheminFichier);
        File fichier = getFichierValide(cheminFichier);
        List<String> lignes = lireLignes(fichier);
        validerContenu(lignes, cheminFichier);
        chargerQuestionsDepuisLignes(lignes);
        return lignes;
    }

    private void validerChemin(String chemin) throws ExceptionNotFound {
        if (chemin == null || chemin.trim().isEmpty()) {
            throw new ExceptionNotFound("Le chemin fourni est invalide : " + chemin);
        }
    }

    private File getFichierValide(String chemin) throws ExceptionFichierNotExist {
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile()) {
            throw new ExceptionFichierNotExist("Fichier introuvable : " + chemin);
        }
        return fichier;
    }

    private List<String> lireLignes(File fichier) throws ExceptionRecupFail {
        List<String> lignes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (!ligne.trim().isEmpty()) lignes.add(ligne);
            }
        } catch (IOException e) {
            throw new ExceptionRecupFail("Erreur de lecture : " + e.getMessage());
        }
        return lignes;
    }

    private void validerContenu(List<String> lignes, String chemin) throws ExceptionFichierEmpty {
        if (lignes.isEmpty()) {
            throw new ExceptionFichierEmpty("Le fichier est vide : " + chemin);
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
        } catch (ExceptionQuestionInvalid | ExceptionRecupFail e) {
            System.err.println("Ligne ignorée (format invalide) : " + ligne);
        }
    }

    // -------------------------------------------------------
    // chargerQuestion()
    // -------------------------------------------------------

    @Override
    public QuestionDTO chargerQuestion(String ligne)
            throws ExceptionQuestionInvalid, ExceptionRecupFail {
        validerLigne(ligne);
        String[] parts = decouper(ligne);
        return construireQuestion(parts);
    }

    private void validerLigne(String ligne) throws ExceptionQuestionInvalid {
        if (ligne == null || ligne.trim().isEmpty()) {
            throw new ExceptionQuestionInvalid("La ligne est vide ou nulle.");
        }
    }

    private String[] decouper(String ligne) throws ExceptionQuestionInvalid {
        String[] parts = ligne.split(SEPARATEUR, -1);
        if (parts.length < NB_CHAMPS) {
            throw new ExceptionQuestionInvalid(
                    "Format invalide (attendu " + NB_CHAMPS + " champs) : " + ligne);
        }
        return parts;
    }

    private QuestionDTO construireQuestion(String[] parts)
            throws ExceptionQuestionInvalid, ExceptionRecupFail {
        try {
            int id             = parseId(parts[0]);
            String libelle     = parts[1].trim();
            String theme       = parts[2].trim();
            Difficulte diff    = parseDifficulte(parts[3]);
            String reponse     = parts[4].trim();
            String explication = parts[5].trim();
            String reference   = parts[6].trim();
            Langue langue      = parseLangue(parts[7]);
            return new QuestionDTO(id, libelle, theme, diff, reponse, explication, reference, langue);
        } catch (ExceptionQuestionInvalid e) {
            throw e;
        } catch (Exception e) {
            throw new ExceptionRecupFail("Erreur inattendue : " + e.getMessage());
        }
    }

    private int parseId(String valeur) throws ExceptionQuestionInvalid {
        try {
            return Integer.parseInt(valeur.trim());
        } catch (NumberFormatException e) {
            throw new ExceptionQuestionInvalid("ID non entier : " + valeur);
        }
    }

    private Difficulte parseDifficulte(String valeur) throws ExceptionQuestionInvalid {
        try {
            return Difficulte.valueOf(valeur.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ExceptionQuestionInvalid("Difficulté invalide : " + valeur);
        }
    }

    private Langue parseLangue(String valeur) throws ExceptionQuestionInvalid {
        try {
            return Langue.valueOf(valeur.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ExceptionQuestionInvalid("Langue invalide : " + valeur);
        }
    }

    // -------------------------------------------------------
    // getQuestion()
    // -------------------------------------------------------

    @Override
    public QuestionDTO getQuestion() throws ExceptionNotFound {
        verifierQuestionsDisponibles();
        reinitialiserSiNecessaire();
        return questions.get(indexCourant++);
    }

    private void verifierQuestionsDisponibles() throws ExceptionNotFound {
        if (questions == null || questions.isEmpty()) {
            throw new ExceptionNotFound("Aucune question disponible. Chargez d'abord un fichier.");
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