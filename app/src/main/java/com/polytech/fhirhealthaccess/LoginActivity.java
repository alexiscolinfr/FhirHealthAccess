package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private String email,password;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void login(View view){
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        // modifier ici la condition pour l'autentification
        if(true){
            Intent intent = new Intent(LoginActivity.this, ListPatientActivity.class);
            startActivity(intent);
        }
    }
}