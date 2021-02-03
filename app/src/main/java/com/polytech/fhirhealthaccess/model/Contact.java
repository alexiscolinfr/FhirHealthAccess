package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contact {

    @SerializedName("relationship")
    @Expose
    private List<MaritalStatus> relationship;

    @SerializedName("name")
    @Expose
    private Name name;

    @SerializedName("telecom")
    @Expose
    private List<Telecom> telecom;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("period")
    @Expose
    private Period period;

    public Contact(){}
}
