package com.polytech.fhirhealthaccess.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://test.fhir.org/r4/";

    public static PatientService getPatientService(){
        return RetrofitClient.getClient(API_URL).create(PatientService.class);
    }

}
