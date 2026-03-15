package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class ExceptionQuestionInvalid extends Exception {

    public ExceptionQuestionInvalid(String message) {
        super(message);
    }

    public ExceptionQuestionInvalid() {
        super("La question est invalide ou mal formatée.");
    }
}
