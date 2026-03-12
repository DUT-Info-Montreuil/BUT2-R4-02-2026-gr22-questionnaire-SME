package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

/**
 * Exception levée lorsqu'une question est invalide
 */
public class ExceptionQuestionInvalid extends Exception {

    public ExceptionQuestionInvalid() {
        super("Question invalide");
    }

    public ExceptionQuestionInvalid(String message) {
        super(message);
    }

    public ExceptionQuestionInvalid(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionQuestionInvalid(int questionId, String raison) {
        super("Question ID " + questionId + " invalide : " + raison);
    }
}