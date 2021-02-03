package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Type {

    @SerializedName("coding")
    @Expose
    private List<Coding> coding;

    @SerializedName("text")
    @Expose
    private String text;

    public Type(){}
}
