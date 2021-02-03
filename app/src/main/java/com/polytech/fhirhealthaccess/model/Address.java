package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("use")
    @Expose
    private String use;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("line")
    @Expose
    private String[] line;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("district")
    @Expose
    private String district;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("postalCode")
    @Expose
    private String postalCode;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("period")
    @Expose
    private Period period;

    public Address(){}

    public String[] getLine() {
        return line;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setLine(String[] line) {
        this.line = line;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
