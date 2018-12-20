package com.example.jcmilena.apptomodify2;

public class Alumne {

    String nom;
    String cognom;
    String curs;
    String telefon;

    public Alumne(String nom, String cognom, String curs, String telefon) {
        this.nom = nom;
        this.cognom = cognom;
        this.curs = curs;
        this.telefon = telefon;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getCurs() {
        return curs;
    }

    public String getTelefon() {
        return telefon;
    }
}
