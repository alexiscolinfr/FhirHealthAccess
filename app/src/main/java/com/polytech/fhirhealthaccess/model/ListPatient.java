package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListPatient {

    @SerializedName("entry")
    @Expose
    private List<Patient> listPatient;

    public ListPatient(List listPatient) {
        this.listPatient = listPatient;
    }

    public List<Patient> getListPatient() {
        return listPatient;
    }

    public void setListPatient(List<Patient> listPatient) {
        this.listPatient = listPatient;
    }
}
