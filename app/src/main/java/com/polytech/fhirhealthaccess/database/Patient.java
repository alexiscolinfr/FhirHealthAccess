package com.polytech.fhirhealthaccess.database;

public class Patient {

    private String nom,prenom,sexe,dateNaissance,telephone,adresse,etatCivil,langue;
    private boolean actif;

    public Patient(boolean actif, String nom, String prenom, String sexe, String dateNaissance, String telephone, String adresse, String etatCivil, String langue){
        this.actif = actif;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.adresse = adresse;
        this.etatCivil = etatCivil;
        this.langue = langue;
    }

    public boolean isActif() {
        return actif;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEtatCivil() {
        return etatCivil;
    }

    public String getLangue() {
        return langue;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEtatCivil(String etatCivil) {
        this.etatCivil = etatCivil;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
