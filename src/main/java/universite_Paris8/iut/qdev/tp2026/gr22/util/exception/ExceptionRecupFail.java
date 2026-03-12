package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

/**
 * Exception levée lorsque la récupération (recall) échoue
 */
public class ExceptionRecupFail extends Exception {

    public ExceptionRecupFail() {
        super("Échec de la récupération des données");
    }

    public ExceptionRecupFail(String message) {
        super(message);
    }

    public ExceptionRecupFail(String message, Throwable cause) {
        super(message, cause);
    }
}