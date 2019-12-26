package com.example.mathieuetthomas;

public class Client {

    private String nom;
    private String prenom;
    private String date;
    private String ville;
    private String phone;

    public Client(String nom, String prenom, String date, String ville, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.ville = ville;
        if ((phone != null)) {
            this.phone = phone;
        } else {
            this.phone = "000";
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
