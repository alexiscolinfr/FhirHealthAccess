package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.polytech.fhirhealthaccess.database.Patient;

public class DetailsActivity extends AppCompatActivity {

    private Patient patient = ListPatientActivity.selectedPatient;
    private TextView textViewNom, textViewPrenom, textViewSexe, textViewDateNaissance, textViewTelephone, textViewAdresse, textViewEtatCivil, textViewLangue;
    private ImageView imageViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textViewNom = findViewById(R.id.textViewNomPatient);
        textViewPrenom = findViewById(R.id.textViewPrenomPatient);
        textViewSexe = findViewById(R.id.textViewSexePatient);
        textViewDateNaissance = findViewById(R.id.textViewDateNaissancePatient);
        textViewTelephone = findViewById(R.id.textViewTelephonePatient);
        textViewAdresse = findViewById(R.id.textViewAdressePatient);
        textViewEtatCivil = findViewById(R.id.textViewEtatCivilPatient);
        textViewLangue = findViewById(R.id.textViewLanguePatient);
        imageViewStatus = findViewById(R.id.imageViewStatus);

        textViewNom.setText(patient.getNom());
        textViewPrenom.setText(patient.getPrenom());
        textViewSexe.setText(patient.getSexe());
        textViewDateNaissance.setText(patient.getDateNaissance());
        textViewTelephone.setText(patient.getTelephone());
        textViewAdresse.setText(patient.getAdresse());
        textViewEtatCivil.setText(patient.getEtatCivil());
        textViewLangue.setText(patient.getLangue());
        imageViewStatus.setImageResource(getImageId(this,patient.isActif()));
    }

    public static int getImageId(Context c, boolean isActif) {
        String imageName;
        if (isActif)
            imageName = "status_active";
        else
            imageName = "status_inactive";

        return c.getResources().getIdentifier(imageName, "drawable", c.getPackageName());
    }
}