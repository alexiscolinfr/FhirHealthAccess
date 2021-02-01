package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("family")
    @Expose
    private String family;

    @SerializedName("given")
    @Expose
    private String[] given;

    public Name(){}

    public String getFamily() {
        return family;
    }

    public String[] getGiven() {
        return given;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setGiven(String[] given) {
        this.given = given;
    }
}
