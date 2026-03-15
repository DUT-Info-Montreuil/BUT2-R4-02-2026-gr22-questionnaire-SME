package universite_Paris8.iut.qdev.tp2026.gr22.commons.enums;

public enum Langue {
    FRANCAIS(1),
    ANGLAIS(2);

    private final int valeur;

    Langue(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
