package Projet.Utils;

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
