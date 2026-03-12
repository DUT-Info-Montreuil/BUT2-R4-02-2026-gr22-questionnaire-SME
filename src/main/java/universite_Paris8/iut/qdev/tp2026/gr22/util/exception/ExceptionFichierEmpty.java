package universite_Paris8.iut.qdev.tp2026.gr22.util.exception;

/**
 * Exception levée lorsqu'un fichier est vide alors qu'il devrait contenir des données
 */
public class ExceptionFichierEmpty extends Exception {

    public ExceptionFichierEmpty() {
        super("Le fichier est vide");
    }

    public ExceptionFichierEmpty(String nomFichier) {
        super("Le fichier '" + nomFichier + "' est vide");
    }

    public ExceptionFichierEmpty(String message, Throwable cause) {
        super(message, cause);
    }
}
