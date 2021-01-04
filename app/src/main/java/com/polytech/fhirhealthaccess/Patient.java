package com.polytech.fhirhealthaccess;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {

    private String nom,prenom,sexe,telephone,adresse,etatCivil,langue;
    private Date dateNaissance;
    private boolean actif;

    public Patient(boolean actif, String nom, String prenom, String sexe, Date dateNaissance, String telephone, String adresse, String etatCivil, String langue){
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
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(dateNaissance);
        return todayAsString;
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
