package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * UpdatePatientActivity est une interface appelée lorsque l'utilisateur souhaite ajouter un nouveau
 * patient ou modifier la fiche d'un patient déjà existant sur le serveur Fhir.
 *
 * @version 1.0
 */
public class UpdatePatientActivity extends AppCompatActivity {

    private int lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth;
    private Button buttonDateNaissance;
    private ToggleButton toggleButtonStatut;
    private EditText editTextNom, editTextPrenom, editTextTelephone, editTextCity,
            editTextEtatCivil, editTextLangue;
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

        Button buttonUpdatePatient = findViewById(R.id.buttonUpdatePatient);
        buttonDateNaissance = findViewById(R.id.buttonDateNaissance);
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

            // On vérifie que chaque attribut de la classe Patient existe avant de le récupérer
            if (selectedPatient.getResource().getActive() != null)
                toggleButtonStatut.setChecked(selectedPatient.getResource().getActive());
            if (selectedPatient.getResource().getName() != null){
                editTextNom.setText(selectedPatient.getResource().getName().get(0).getFamily());
                editTextPrenom.setText(selectedPatient.getResource().getName().get(0).getGiven()[0]);
            }
            if (selectedPatient.getResource().getGender() != null)
                setPatientGender(selectedPatient.getResource().getGender());
            if (selectedPatient.getResource().getBirthDate() != null)
                buttonDateNaissance.setText(selectedPatient.getResource().getBirthDate());
            if(selectedPatient.getResource().getTelecom() != null)
                editTextTelephone.setText(selectedPatient.getResource().getTelecom().get(0).getValue());
            if(selectedPatient.getResource().getAddress() != null)
                editTextCity.setText(selectedPatient.getResource().getAddress().get(0).getCity());
            if(selectedPatient.getResource().getMaritalStatus() != null)
                editTextEtatCivil.setText(selectedPatient.getResource().getMaritalStatus().getText());
            if(selectedPatient.getResource().getCommunication() != null)
                editTextLangue.setText(selectedPatient.getResource().getCommunication().get(0)
                        .getLanguage().getText());
        }
    }

    /**
     * Cette méthode s'exécute lorsqu'un utilisateur veut renseigner la date de naissance d'un patient.
     * Elle ouvre une fenêtre avec un calendrier permettant de saisir facilement une date.
     *
     * @param view Vue de l'interface sur laquelle l'utilisateur a cliqué.
     */
    public void setBirthdate (View view){

        // Récupération de la date actuelle
        final Calendar c = Calendar.getInstance();
        lastSelectedYear = c.get(Calendar.YEAR);
        lastSelectedMonth = c.get(Calendar.MONTH);
        lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        // Création du date listener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                buttonDateNaissance.setText(year + "-" + String.format("%02d", dayOfMonth) + "-"
                        + String.format("%02d", monthOfYear + 1));

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        // Création de la boîte de dialogue
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        // Affichage de la boîte de dialogue
        datePickerDialog.show();
    }

    /**
     * Cette méthode s'exécute lorsqu'un utilisateur clique sur le bouton "Modifier" ou "Ajouter"
     * après avoir ajouté des informations dans la fiche d'un patient.
     * Elle extrait les valeurs renseignées par l'utilisateur (i.e. nom, prénom, sexe, …) puis créé
     * un nouveau patient ou modifie les valeurs du patient séléctionné et l'envoie au serveur Fhir.
     *
     * @param view Vue de l'interface sur laquelle l'utilisateur a cliqué.
     */
    public void updatePatient (View view) {
        boolean actif = toggleButtonStatut.isChecked();
        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String sexe = getPatientGender(radioGroupSexe).trim();
        String dateNaissance = buttonDateNaissance.getText().toString().trim();
        String telephone = editTextTelephone.getText().toString().trim();
        String ville = editTextCity.getText().toString().trim();
        String etatCivil = editTextEtatCivil.getText().toString().trim();
        String langue = editTextLangue.getText().toString().trim();

        if(isNewPatient) {
            // Création d'un objet Name contenant les nom et prénom du patient
            Name name = new Name();
            name.setGiven(new String[]{prenom});
            name.setFamily(nom);
            List<Name> nameList = new ArrayList<>();
            nameList.add(name);

            // Création d'un objet Telecom contenant le numéro de téléphone du patient
            Telecom telecom = new Telecom();
            telecom.setValue(telephone);
            List<Telecom> telecomList = new ArrayList<>();
            telecomList.add(telecom);

            // Création d'un objet Address contenant la ville du patient
            Address address = new Address();
            address.setCity(ville);
            List<Address> addressList = new ArrayList<>();
            addressList.add(address);

            // Création d'un objet MaritalStatus contenant l'état civil du patient
            MaritalStatus maritalStatus = new MaritalStatus();
            maritalStatus.setText(etatCivil);

            // Création d'un objet Language et Communication contenant la langue parlée par le patient
            Language language = new Language();
            language.setText(langue);
            Communication communication = new Communication();
            communication.setLanguage(language);
            List<Communication> communicationList = new ArrayList<>();
            communicationList.add(communication);

            // Création d'un objet Ressource contenant tous les objets créés précédemment
            Resource newResourcePatient = new Resource();
            newResourcePatient.setResourceType("Patient");
            newResourcePatient.setActive(actif);
            newResourcePatient.setName(nameList);
            newResourcePatient.setGender(sexe);
            newResourcePatient.setBirthDate(dateNaissance);
            newResourcePatient.setTelecom(telecomList);
            newResourcePatient.setAddress(addressList);
            newResourcePatient.setMaritalStatus(maritalStatus);
            newResourcePatient.setCommunication(communicationList);

            Call<Patient> call = patientService.addPatient(newResourcePatient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(
                                UpdatePatientActivity.this,
                                "Patient créé avec succès !",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(
                                UpdatePatientActivity.this,
                                "Une erreur s'est produite, le patient n'a pas pu être créé.",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e("ERROR: ", Objects.requireNonNull(t.getMessage()));
                }
            });

            finish();
        }
        else {
            Resource updatedResourcePatient = selectedPatient.getResource();

            updatedResourcePatient.setActive(actif);
            updatedResourcePatient.setGender(sexe);
            updatedResourcePatient.setBirthDate(dateNaissance);

            // Pour chaque attribut du patient à modifier, on vérifie qu'il existe
            if (selectedPatient.getResource().getName() != null){
                updatedResourcePatient.getName().get(0).setFamily(nom);
                updatedResourcePatient.getName().get(0).setGiven(new String[]{prenom});
            } else {
                // Sinon on créé un nouvel objet puis on l'ajoute au patient
                Name name = new Name();
                name.setGiven(new String[]{prenom});
                name.setFamily(nom);
                List<Name> nameList = new ArrayList<>();
                nameList.add(name);
                updatedResourcePatient.setName(nameList);
            }

            if(selectedPatient.getResource().getTelecom() != null) {
                updatedResourcePatient.getTelecom().get(0).setValue(telephone);
            } else {
                Telecom telecom = new Telecom();
                telecom.setValue(telephone);
                List<Telecom> telecomList = new ArrayList<>();
                telecomList.add(telecom);
                updatedResourcePatient.setTelecom(telecomList);
            }

            if(selectedPatient.getResource().getAddress() != null) {
                updatedResourcePatient.getAddress().get(0).setCity(ville);
            } else {
                Address address = new Address();
                address.setCity(ville);
                List<Address> addressList = new ArrayList<>();
                addressList.add(address);
                updatedResourcePatient.setAddress(addressList);
            }

            if(selectedPatient.getResource().getMaritalStatus() != null) {
                updatedResourcePatient.getMaritalStatus().setText(etatCivil);
            } else {
                MaritalStatus maritalStatus = new MaritalStatus();
                maritalStatus.setText(etatCivil);
                updatedResourcePatient.setMaritalStatus(maritalStatus);
            }

            if(selectedPatient.getResource().getCommunication() != null) {
                updatedResourcePatient.getCommunication().get(0).getLanguage().setText(langue);
            } else {
                Language language = new Language();
                language.setText(langue);
                Communication communication = new Communication();
                communication.setLanguage(language);
                List<Communication> communicationList = new ArrayList<>();
                communicationList.add(communication);
                updatedResourcePatient.setCommunication(communicationList);
            }

            Call<Patient> call = patientService.updatePatient(selectedPatient.getResource().getId(),
                    updatedResourcePatient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(
                                UpdatePatientActivity.this,
                                "Patient mis à jour avec succès !",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(
                                UpdatePatientActivity.this,
                                "Une erreur s'est produite, le patient n'a pas pu être mis à jour.",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e("ERROR: ", Objects.requireNonNull(t.getMessage()));
                }
            });

            Intent intent = new Intent(this, ListPatientActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * Cette méthode renvoie le sexe du patient au format texte en fonction du bouton séléctionné
     * dans le formulaire (i.e. homme, femme ou autre).
     *
     * @param radioGroup Groupe des boutons radio du formulaire
     * @return String Sexe du patient
     */
    public String getPatientGender(RadioGroup radioGroup) {
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

    /**
     * Cette méthode coche le bouton du formulaire correspondant au sexe du patient.
     *
     * @param gender Sexe du patient
     */
    public void setPatientGender(String gender) {
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