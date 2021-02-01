package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaritalStatus {

    @SerializedName("text")
    @Expose
    private String text;

    public MaritalStatus(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
