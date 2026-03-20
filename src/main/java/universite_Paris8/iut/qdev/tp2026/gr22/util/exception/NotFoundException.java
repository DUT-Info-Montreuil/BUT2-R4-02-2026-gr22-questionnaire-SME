package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Emplacement introuvable.");
    }
}
