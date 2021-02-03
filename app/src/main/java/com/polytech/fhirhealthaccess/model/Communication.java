package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Communication {

    @SerializedName("language")
    @Expose
    private Language language;

    @SerializedName("preferred")
    @Expose
    private boolean preferred;

    public Communication(){}

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
