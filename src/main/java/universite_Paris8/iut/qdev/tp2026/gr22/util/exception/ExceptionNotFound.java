package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class ExceptionNotFound extends Exception {

    public ExceptionNotFound(String message) {
        super(message);
    }

    public ExceptionNotFound() {
        super("Emplacement introuvable.");
    }
}
