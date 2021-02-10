package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Period {

    @SerializedName("start")
    @Expose
    private String start;

    @SerializedName("end")
    @Expose
    private String end;

    public Period() {}
}
