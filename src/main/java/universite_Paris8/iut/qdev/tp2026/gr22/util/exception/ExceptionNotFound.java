package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

/**
 * Exception levée lorsqu'un élément n'est pas trouvé
 */
public class ExceptionNotFound extends Exception {

    public ExceptionNotFound() {
        super("Élément non trouvé");
    }

    public ExceptionNotFound(String message) {
        super(message);
    }

    public ExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}