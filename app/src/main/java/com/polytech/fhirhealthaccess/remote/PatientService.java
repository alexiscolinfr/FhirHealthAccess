package com.polytech.fhirhealthaccess.remote;

import com.polytech.fhirhealthaccess.model.Patient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PatientService {

    @GET("Patient/")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<List<Patient>> getPatients();

    @POST("Patient/")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<Patient> addPatient(@Body Patient patient);

    @PUT("Patient/{id}")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<Patient> updatePatient(@Path("id") String id, @Body Patient patient);

    @DELETE("Patient/{id}")
    @Headers({"Accept: application/fhir+json","Content-Type: application/fhir+json"})
    Call<Patient> deletePatient(@Path("id") String id);
}
