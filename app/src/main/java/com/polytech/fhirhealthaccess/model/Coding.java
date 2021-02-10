package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coding {

    @SerializedName("system")
    @Expose
    private String system;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("display")
    @Expose
    private String display;

    @SerializedName("userSelected")
    @Expose
    private boolean userSelected;

    public Coding() {}
}
