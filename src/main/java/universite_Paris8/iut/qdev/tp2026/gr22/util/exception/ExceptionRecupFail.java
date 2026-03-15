package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class ExceptionRecupFail extends Exception {

    public ExceptionRecupFail(String message) {
        super(message);
    }

    public ExceptionRecupFail() {
        super("La récupération des données a échoué.");
    }
}