package universite_Paris8.iut.qdev.tp2026.gr22.commons.enums;

public enum LangueEnum {
    FRANCAIS(1),
    ANGLAIS(2);

    private final int valeur;

    LangueEnum(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
}
