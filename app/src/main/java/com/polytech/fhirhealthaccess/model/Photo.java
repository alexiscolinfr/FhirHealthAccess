package com.polytech.fhirhealthaccess.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("contentType")
    @Expose
    private String contentType;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("data")
    @Expose
    private String data;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("size")
    @Expose
    private int size;

    @SerializedName("hash")
    @Expose
    private String hash;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("creation")
    @Expose
    private String creation;

    public Photo(){}
}
