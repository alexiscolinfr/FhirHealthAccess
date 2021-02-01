package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("text")
    @Expose
    private String text;

    public Language(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
