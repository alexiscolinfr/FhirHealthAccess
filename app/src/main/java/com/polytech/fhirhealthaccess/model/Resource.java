package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Resource {

    @SerializedName("resourceType")
    @Expose
    private String resourceType;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("identifier")
    @Expose
    private List<Identifier> identifier;

    @SerializedName("active")
    @Expose
    private boolean active;

    @SerializedName("name")
    @Expose
    private List<Name> names;

    @SerializedName("telecom")
    @Expose
    private List<Telecom> telecoms;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birthDate")
    @Expose
    private String birthDate;

    @SerializedName("deceasedBoolean")
    @Expose
    private boolean deceasedBoolean;

    @SerializedName("address")
    @Expose
    private List<Address> address;

    @SerializedName("maritalStatus")
    @Expose
    private MaritalStatus maritalStatus;

    @SerializedName("multipleBirthBoolean")
    @Expose
    private boolean multipleBirthBoolean;

    @SerializedName("photo")
    @Expose
    private List<Photo> photo;

    @SerializedName("contact")
    @Expose
    private List<Contact> contact;

    @SerializedName("communication")
    @Expose
    private List<Communication> communication;

    public Resource() {}

    public String getResourceType() {
        return resourceType;
    }

    public String getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public List<Name> getName() {
        return names;
    }

    public List<Telecom> getTelecom() {
        return telecoms;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<Address> getAddress() {
        return address;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setName(List<Name> name) {
        this.names = name;
    }

    public void setTelecom(List<Telecom> telecom) {
        this.telecoms = telecom;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setCommunication(List<Communication> communication) {
        this.communication = communication;
    }
}
