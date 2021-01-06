package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.polytech.fhirhealthaccess.database.Patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListPatientActivity extends AppCompatActivity {

    private ListView listView;
    private SearchView searchView;
    private PatientAdapter adapter;
    public static Patient selectedPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patient);

        listView = findViewById(R.id.listViewPatient);
        searchView = findViewById(R.id.searchBar);
        listView.setTextFilterEnabled(true);
        searchView.setIconifiedByDefault(false);

        getPatientsList();

        // Rend les éléments de la liste cliquable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPatient = adapter.getItem(position);
                Intent intent = new Intent(ListPatientActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

        // Lorsque du texte est inséré dans la barre de recherche, la liste est automatiquement filtée
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_patient, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent1 = new Intent(this, UpdatePatientActivity.class);
                intent1.putExtra("isNewPatient",true);
                startActivity(intent1);
                return true;
            case R.id.disconnect:
                Intent intent2 = new Intent(this, LoginActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                return true;
            case R.id.about:
                createAlertDialog(this);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void createAlertDialog(Context mContext){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setTitle("À propos");
        String message = "Cette application a été réalisée dans le cadre du cours " +
                "<b>Développement d'applications de gestion de santé</b> de Mr FEBVAY.<br><br>"+
                "<u>Équipe</u> : Omran HAIDARI, Mehdi BENMECHERI & Alexis COLIN";
        builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
        builder.setPositiveButton("Fermer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void getPatientsList(){
        List<Patient> listPatients = new ArrayList<>();

        // TODO : Récupérer ici tous les patients depuis le serveur Fhir

        adapter = new PatientAdapter(ListPatientActivity.this, listPatients);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPatientsList();
    }
}