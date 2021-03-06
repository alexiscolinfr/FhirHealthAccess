package com.polytech.fhirhealthaccess.remote;

import com.polytech.fhirhealthaccess.model.ListPatient;
import com.polytech.fhirhealthaccess.model.Patient;
import com.polytech.fhirhealthaccess.model.Resource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PatientService {

    @GET("Patient/")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<ListPatient> getPatients();

    @GET("Patient/")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<ListPatient> getPatientsFromName(@Query("name") String name);

    @POST("Patient/")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<Patient> addPatient(@Body Resource ressourcePatient);

    @PUT("Patient/{id}")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<Patient> updatePatient(@Path("id") String id, @Body Resource ressourcePatient);

    @DELETE("Patient/{id}")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<Patient> deletePatient(@Path("id") String id);
}
