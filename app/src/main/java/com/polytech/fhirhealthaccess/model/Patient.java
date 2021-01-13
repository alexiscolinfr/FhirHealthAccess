package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Patient {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("active")
    @Expose
    private boolean actif;

    @SerializedName("family")
    @Expose
    private String nom;

    @SerializedName("given")
    @Expose
    private String prenom;

    @SerializedName("gender")
    @Expose
    private String sexe;

    @SerializedName("birthDate")
    @Expose
    private Date dateNaissance;

    @SerializedName("telecom")
    @Expose
    private String telephone;

    @SerializedName("address")
    @Expose
    private String adresse;

    @SerializedName("maritalStatus")
    @Expose
    private String etatCivil;

    @SerializedName("language")
    @Expose
    private String langue;

    public Patient(String id, boolean actif, String nom, String prenom, String sexe, Date dateNaissance, String telephone, String adresse, String etatCivil, String langue){
        this.id = id;
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

    public String getId() {
        return id;
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
        return new SimpleDateFormat(pattern).format(dateNaissance);
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

    public void setId(String id) {
        this.id = id;
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

    public void setDateNaissance(String dateNaissance) throws ParseException {
        this.dateNaissance = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse(dateNaissance);
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
