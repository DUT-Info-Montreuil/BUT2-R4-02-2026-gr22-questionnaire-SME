package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

public class ExceptionFichierNotExist extends Exception {

    public ExceptionFichierNotExist(String message) {
        super(message);
    }

    public ExceptionFichierNotExist() {
        super("Le fichier n'existe pas.");
    }
}