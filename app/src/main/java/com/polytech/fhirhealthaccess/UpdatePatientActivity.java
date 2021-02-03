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

import com.polytech.fhirhealthaccess.model.Address;
import com.polytech.fhirhealthaccess.model.Communication;
import com.polytech.fhirhealthaccess.model.Language;
import com.polytech.fhirhealthaccess.model.MaritalStatus;
import com.polytech.fhirhealthaccess.model.Name;
import com.polytech.fhirhealthaccess.model.Patient;
import com.polytech.fhirhealthaccess.model.Resource;
import com.polytech.fhirhealthaccess.model.Telecom;
import com.polytech.fhirhealthaccess.remote.APIUtils;
import com.polytech.fhirhealthaccess.remote.PatientService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePatientActivity extends AppCompatActivity {

    private int lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth;
    private Button buttonDateNaissance, buttonUpdatePatient;
    private ToggleButton toggleButtonStatut;
    private EditText editTextNom, editTextPrenom,editTextTelephone,editTextCity,editTextEtatCivil,editTextLangue;
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
        editTextCity = findViewById(R.id.editTextCity);
        editTextEtatCivil = findViewById(R.id.editTextEtatCivil);
        editTextLangue = findViewById(R.id.editTextLangue);

        if (isNewPatient){
            buttonUpdatePatient.setText(getString(R.string.add));
        }
        else{
            buttonUpdatePatient.setText(getString(R.string.edit));
            toggleButtonStatut.setChecked(selectedPatient.getResource().getActive());
            if (selectedPatient.getResource().getName() != null){
                editTextNom.setText(selectedPatient.getResource().getName().get(0).getFamily());
                editTextPrenom.setText(selectedPatient.getResource().getName().get(0).getGiven()[0]);
            }

            setPatientGender(selectedPatient.getResource().getGender());
            buttonDateNaissance.setText(selectedPatient.getResource().getBirthDate());
            if(selectedPatient.getResource().getTelecom() != null)
                editTextTelephone.setText(selectedPatient.getResource().getTelecom().get(0).getValue());
            if(selectedPatient.getResource().getAddress() != null)
                editTextCity.setText(selectedPatient.getResource().getAddress().get(0).getCity());
            if(selectedPatient.getResource().getMaritalStatus() != null)
                editTextEtatCivil.setText(selectedPatient.getResource().getMaritalStatus().getText());
            if(selectedPatient.getResource().getCommunication() != null)
                editTextLangue.setText(selectedPatient.getResource().getCommunication().get(0).getLanguage().getText());
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
                buttonDateNaissance.setText(year + "-" + String.format("%02d", dayOfMonth) + "-" + String.format("%02d", monthOfYear + 1));

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
        String dateNaissance = buttonDateNaissance.getText().toString().trim();
        String telephone = editTextTelephone.getText().toString().trim();
        String ville = editTextCity.getText().toString().trim();
        String etatCivil = editTextEtatCivil.getText().toString().trim();
        String langue = editTextLangue.getText().toString().trim();

        if(isNewPatient){
            Resource newResourcePatient = new Resource();
            newResourcePatient.setResourceType("Patient");
            newResourcePatient.setActive(actif);
            Name name = new Name();
            name.setGiven(new String[]{prenom});
            name.setFamily(nom);
            List<Name> nameList = new ArrayList<>();
            nameList.add(name);
            newResourcePatient.setName(nameList);
            newResourcePatient.setGender(sexe);
            newResourcePatient.setBirthDate(dateNaissance);
            Telecom telecom = new Telecom();
            telecom.setValue(telephone);
            List<Telecom> telecomList = new ArrayList<>();
            telecomList.add(telecom);
            newResourcePatient.setTelecom(telecomList);
            Address address = new Address();
            address.setCity(ville);
            List<Address> addressList = new ArrayList<>();
            addressList.add(address);
            newResourcePatient.setAddress(addressList);
            MaritalStatus maritalStatus = new MaritalStatus();
            maritalStatus.setText(etatCivil);
            newResourcePatient.setMaritalStatus(maritalStatus);
            Language language = new Language();
            language.setText(langue);
            Communication communication = new Communication();
            communication.setLanguage(language);
            List<Communication> communicationList = new ArrayList<>();
            communicationList.add(communication);
            newResourcePatient.setCommunication(communicationList);

            Call<Patient> call = patientService.addPatient(newResourcePatient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(UpdatePatientActivity.this, "Patient créé avec succès !", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UpdatePatientActivity.this, "Une erreur s'est produite, le patient n'a pas pu être créé.", Toast.LENGTH_SHORT).show();
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
            Resource updatedResourcePatient = selectedPatient.getResource();
            updatedResourcePatient.setActive(actif);
            if (selectedPatient.getResource().getName() != null){
                updatedResourcePatient.getName().get(0).setFamily(nom);
                updatedResourcePatient.getName().get(0).setGiven(new String[]{prenom});
            }
            updatedResourcePatient.setGender(sexe);
            updatedResourcePatient.setBirthDate(dateNaissance);
            if(selectedPatient.getResource().getTelecom() != null)
                updatedResourcePatient.getTelecom().get(0).setValue(telephone);
            if(selectedPatient.getResource().getAddress() != null)
                updatedResourcePatient.getAddress().get(0).setCity(ville);
            if(selectedPatient.getResource().getMaritalStatus() != null)
                updatedResourcePatient.getMaritalStatus().setText(etatCivil);
            if(selectedPatient.getResource().getCommunication() != null)
                updatedResourcePatient.getCommunication().get(0).getLanguage().setText(langue);

            Call<Patient> call = patientService.updatePatient(selectedPatient.getResource().getId(), updatedResourcePatient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(UpdatePatientActivity.this, "Patient mis à jour avec succès !", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UpdatePatientActivity.this, "Une erreur s'est produite, le patient n'a pas pu être mis à jour.", Toast.LENGTH_SHORT).show();
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
                sexe = "male";
                break;
            case R.id.radioButtonF:
                sexe = "female";
                break;
            default:
                sexe = "other";
                break;
        }
        return sexe;
    }

    public void setPatientGender(String gender){
        switch (gender) {
            case "male":
                radioGroupSexe.check(R.id.radioButtonH);
                break;
            case "female":
                radioGroupSexe.check(R.id.radioButtonF);
                break;
            default:
                radioGroupSexe.check(R.id.radioButtonA);
                break;
        }
    }
}