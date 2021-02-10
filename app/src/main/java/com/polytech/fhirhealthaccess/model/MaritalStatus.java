package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MaritalStatus {

    @SerializedName("coding")
    @Expose
    private List<Coding> coding;

    @SerializedName("text")
    @Expose
    private String text;

    public MaritalStatus() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
