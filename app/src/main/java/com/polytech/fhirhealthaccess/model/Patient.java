package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {

    @SerializedName("resource")
    @Expose
    private Resource resource;

    public Patient(){
    }

    public Resource getResource() {
        return resource;
    }
}
