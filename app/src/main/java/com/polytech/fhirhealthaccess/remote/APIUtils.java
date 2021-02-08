package com.polytech.fhirhealthaccess.remote;

public class APIUtils {

    private APIUtils(){}

    public static final String API_URL = "https://stu3.test.pyrohealth.net/fhir/";

    public static PatientService getPatientService(){
        return RetrofitClient.getClient(API_URL).create(PatientService.class);
    }

}
