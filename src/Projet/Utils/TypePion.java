package Projet.Utils;

/**
 * Created by Zwitterion on 26/03/16.
 */
public enum TypePion {

    RECTANGULAIRE("Rectangle"), CERCLE("Cercle");

    private String description;

    TypePion(String value) {
        description = value;
    }

    @Override
    public String toString() {
        return description;
    }

}
