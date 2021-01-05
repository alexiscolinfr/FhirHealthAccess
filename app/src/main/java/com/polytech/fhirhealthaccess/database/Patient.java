package com.polytech.fhirhealthaccess.database;

import com.orm.SugarRecord;

public class Patient extends SugarRecord {

    String nom,prenom,sexe,dateNaissance,telephone,adresse,etatCivil,langue;
    boolean actif;

    public Patient(){}

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
}
