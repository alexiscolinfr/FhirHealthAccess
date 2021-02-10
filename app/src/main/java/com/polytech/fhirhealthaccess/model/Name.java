package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("use")
    @Expose
    private String use;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("family")
    @Expose
    private String family;

    @SerializedName("given")
    @Expose
    private String[] given;

    @SerializedName("prefix")
    @Expose
    private String[] prefix;

    @SerializedName("suffix")
    @Expose
    private String[] suffix;

    @SerializedName("period")
    @Expose
    private Period period;

    public Name() {}

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
