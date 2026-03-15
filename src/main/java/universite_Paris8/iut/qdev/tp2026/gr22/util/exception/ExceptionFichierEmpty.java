package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class ExceptionFichierEmpty extends Exception {

    public ExceptionFichierEmpty(String message) {
        super(message);
    }

    public ExceptionFichierEmpty() {
        super("Le fichier est vide.");
    }
}
