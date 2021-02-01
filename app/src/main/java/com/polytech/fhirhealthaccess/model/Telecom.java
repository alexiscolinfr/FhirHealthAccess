package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Telecom {

    @SerializedName("value")
    @Expose
    private String value;

    public Telecom(){}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
