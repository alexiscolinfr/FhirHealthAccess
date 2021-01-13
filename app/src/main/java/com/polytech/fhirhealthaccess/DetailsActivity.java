package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.polytech.fhirhealthaccess.model.Patient;
import com.polytech.fhirhealthaccess.remote.APIUtils;
import com.polytech.fhirhealthaccess.remote.PatientService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private Patient selectedPatient;
    private TextView textViewNom, textViewPrenom, textViewSexe, textViewDateNaissance, textViewTelephone, textViewAdresse, textViewEtatCivil, textViewLangue;
    private ImageView imageViewStatus;
    private PatientService patientService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        patientService = APIUtils.getPatientService();

        selectedPatient = ListPatientActivity.selectedPatient;

        textViewNom = findViewById(R.id.textViewNomPatient);
        textViewPrenom = findViewById(R.id.textViewPrenomPatient);
        textViewSexe = findViewById(R.id.textViewSexePatient);
        textViewDateNaissance = findViewById(R.id.textViewDateNaissancePatient);
        textViewTelephone = findViewById(R.id.textViewTelephonePatient);
        textViewAdresse = findViewById(R.id.textViewAdressePatient);
        textViewEtatCivil = findViewById(R.id.textViewEtatCivilPatient);
        textViewLangue = findViewById(R.id.textViewLanguePatient);
        imageViewStatus = findViewById(R.id.imageViewStatus);

        textViewNom.setText(selectedPatient.getNom());
        textViewPrenom.setText(selectedPatient.getPrenom());
        textViewSexe.setText(selectedPatient.getSexe());
        textViewDateNaissance.setText(selectedPatient.getDateNaissance());
        textViewTelephone.setText(selectedPatient.getTelephone());
        textViewAdresse.setText(selectedPatient.getAdresse());
        textViewEtatCivil.setText(selectedPatient.getEtatCivil());
        textViewLangue.setText(selectedPatient.getLangue());
        imageViewStatus.setImageResource(getImageId(this,selectedPatient.isActif()));
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
                deletePatient(selectedPatient.getId());
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void deletePatient(String id){
        Call<Patient> call = patientService.deletePatient(id);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if(response.isSuccessful()){
                    Toast.makeText(DetailsActivity.this, "Patient deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
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