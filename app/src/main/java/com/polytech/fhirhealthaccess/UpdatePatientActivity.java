package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.polytech.fhirhealthaccess.model.Patient;
import com.polytech.fhirhealthaccess.remote.APIUtils;
import com.polytech.fhirhealthaccess.remote.PatientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePatientActivity extends AppCompatActivity {

    private int lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth;
    private Button buttonDateNaissance, buttonUpdatePatient;
    private ToggleButton toggleButtonStatut;
    private EditText editTextNom, editTextPrenom,editTextTelephone,editTextAdresse,editTextEtatCivil,editTextLangue;
    private RadioGroup radioGroupSexe;
    private boolean isNewPatient;
    private Patient selectedPatient = ListPatientActivity.selectedPatient;
    private PatientService patientService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);

        final Intent intent = getIntent();
        isNewPatient = intent.getBooleanExtra("isNewPatient",true);

        patientService = APIUtils.getPatientService();

        buttonDateNaissance = findViewById(R.id.buttonDateNaissance);
        buttonUpdatePatient = findViewById(R.id.buttonUpdatePatient);
        toggleButtonStatut = findViewById(R.id.toggleButtonStatut);
        radioGroupSexe = findViewById(R.id.radioGroupeSexe);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextTelephone = findViewById(R.id.editTextTelephone);
        editTextAdresse = findViewById(R.id.editTextAdresse);
        editTextEtatCivil = findViewById(R.id.editTextEtatCivil);
        editTextLangue = findViewById(R.id.editTextLangue);

        if (isNewPatient){
            buttonUpdatePatient.setText(getString(R.string.add));
        }
        else{
            buttonUpdatePatient.setText(getString(R.string.edit));
            toggleButtonStatut.setChecked(selectedPatient.isActif());
            editTextNom.setText(selectedPatient.getNom());
            editTextPrenom.setText(selectedPatient.getPrenom());
            setPatientGender(selectedPatient.getSexe());
            buttonDateNaissance.setText(selectedPatient.getDateNaissance());
            editTextTelephone.setText(selectedPatient.getTelephone());
            editTextAdresse.setText(selectedPatient.getAdresse());
            editTextEtatCivil.setText(selectedPatient.getEtatCivil());
            editTextLangue.setText(selectedPatient.getLangue());
        }
    }

    public void setBirthdate (View view){

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        lastSelectedYear = c.get(Calendar.YEAR);
        lastSelectedMonth = c.get(Calendar.MONTH);
        lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                buttonDateNaissance.setText(String.format("%02d", dayOfMonth) + "/" + String.format("%02d", monthOfYear + 1) + "/" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }

    public void updatePatient (View view) throws ParseException {
        boolean actif = toggleButtonStatut.isChecked();
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String sexe = getPatientGender(radioGroupSexe).trim();
        Date dateNaissance = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).parse(buttonDateNaissance.getText().toString().trim());
        String telephone = editTextTelephone.getText().toString().trim();
        String adresse = editTextAdresse.getText().toString().trim();
        String etatCivil = editTextEtatCivil.getText().toString().trim();
        String langue = editTextLangue.getText().toString().trim();

        if(isNewPatient){
            String uniqueID = UUID.randomUUID().toString();
            Patient patient = new Patient(uniqueID,actif,nom,prenom,sexe,dateNaissance,telephone,adresse,etatCivil,langue);

            Call<Patient> call = patientService.addPatient(patient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(UpdatePatientActivity.this, "Patient created successfully!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });

            finish();
        }
        else{
            Patient patient = new Patient(selectedPatient.getId(),actif,nom,prenom,sexe,dateNaissance,telephone,adresse,etatCivil,langue);

            Call<Patient> call = patientService.updatePatient(selectedPatient.getId(), patient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(UpdatePatientActivity.this, "Patient updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                }
            });

            Intent intent = new Intent(this, ListPatientActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public String getPatientGender(RadioGroup radioGroup){
        int id = radioGroup.getCheckedRadioButtonId();
        String sexe;
        switch (id) {
            case R.id.radioButtonH:
                sexe = "Homme";
                break;
            case R.id.radioButtonF:
                sexe = "Femme";
                break;
            default:
                sexe = "Autre";
                break;
        }
        return sexe;
    }

    public void setPatientGender(String gender){
        switch (gender) {
            case "Homme":
                radioGroupSexe.check(R.id.radioButtonH);
                break;
            case "Femme":
                radioGroupSexe.check(R.id.radioButtonF);
                break;
            default:
                radioGroupSexe.check(R.id.radioButtonA);
                break;
        }
    }
}