package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Telecom {

    @SerializedName("system")
    @Expose
    private String system;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("use")
    @Expose
    private String use;

    @SerializedName("rank")
    @Expose
    private int rank;

    @SerializedName("period")
    @Expose
    private Period period;

    public Telecom(){}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
