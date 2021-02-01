package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Identifier {

    @SerializedName("use")
    @Expose
    private String use;

    @SerializedName("system")
    @Expose
    private String system;

    @SerializedName("value")
    @Expose
    private String value;

    public Identifier(){}

}
