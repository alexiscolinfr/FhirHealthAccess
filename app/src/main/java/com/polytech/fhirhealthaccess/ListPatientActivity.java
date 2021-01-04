package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListPatientActivity extends AppCompatActivity {

    private ListView listView;
    private PatientAdapter adapter;
    public static Patient selectedPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patient);

        List<Patient> patients = new ArrayList<>();

        listView = findViewById(R.id.listViewPatient);
        adapter = new PatientAdapter(ListPatientActivity.this, patients);
        listView.setAdapter(adapter);

        // Rend les éléments de la liste cliquable
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPatient = adapter.getItem(position);
                Intent intent = new Intent(ListPatientActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}