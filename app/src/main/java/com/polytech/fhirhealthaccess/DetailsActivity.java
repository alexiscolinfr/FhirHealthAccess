package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.polytech.fhirhealthaccess.database.Patient;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private Patient patient;
    private TextView textViewNom, textViewPrenom, textViewSexe, textViewDateNaissance, textViewTelephone, textViewAdresse, textViewEtatCivil, textViewLangue;
    private ImageView imageViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        patient = ListPatientActivity.selectedPatient;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this, UpdatePatientActivity.class);
                intent.putExtra("isNewPatient",false);
                startActivity(intent);
                return true;
            case R.id.delete:
                deletePatient();
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deletePatient(){

        // TODO : Supprimer le patient du serveur Fhir ici

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