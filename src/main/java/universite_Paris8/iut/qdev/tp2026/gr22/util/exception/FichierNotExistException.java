package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class FichierNotExistException extends Exception {

    public FichierNotExistException(String message) {
        super(message);
    }

    public FichierNotExistException() {
        super("Le fichier n'existe pas.");
    }
}