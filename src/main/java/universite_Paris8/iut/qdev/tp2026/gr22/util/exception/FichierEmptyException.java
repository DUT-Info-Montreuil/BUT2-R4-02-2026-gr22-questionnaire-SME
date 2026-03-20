package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class FichierEmptyException extends Exception {

    public FichierEmptyException(String message) {
        super(message);
    }

    public FichierEmptyException() {
        super("Le fichier est vide.");
    }
}
