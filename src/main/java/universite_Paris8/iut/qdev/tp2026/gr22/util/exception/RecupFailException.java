package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class RecupFailException extends Exception {

    public RecupFailException(String message) {
        super(message);
    }

    public RecupFailException() {
        super("La récupération des données a échoué.");
    }
}