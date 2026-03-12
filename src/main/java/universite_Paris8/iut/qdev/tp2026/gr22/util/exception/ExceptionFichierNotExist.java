package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;


/**
 * Exception levée lorsqu'un fichier n'existe pas
 */
public class ExceptionFichierNotExist extends Exception {

    public ExceptionFichierNotExist() {
        super("Le fichier n'existe pas");
    }

    public ExceptionFichierNotExist(String nomFichier) {
        super("Le fichier '" + nomFichier + "' n'existe pas");
    }

    public ExceptionFichierNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}