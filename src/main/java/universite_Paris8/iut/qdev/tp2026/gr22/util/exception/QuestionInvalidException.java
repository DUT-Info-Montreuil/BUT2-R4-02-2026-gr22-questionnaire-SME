package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class QuestionInvalidException extends Exception {

    public QuestionInvalidException(String message) {
        super(message);
    }

    public QuestionInvalidException() {
        super("La question est invalide ou mal formatée.");
    }
}
