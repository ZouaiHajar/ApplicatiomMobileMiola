package com.example.newmiolaapp.Modal;

public class prof {

    private String nom;
    private String prenom;
    private String email;

    public prof(String nom ,String prenom ,String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
