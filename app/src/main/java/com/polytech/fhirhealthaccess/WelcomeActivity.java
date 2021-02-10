package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * WelcomeActivity est appelée dès le lancement de l'application. Cette clase correspond à l'écran
 * de chargement de l'application. Elle redirige ensuite vers l'écran de connexion.
 *
 * @version 1.0
 */

public class WelcomeActivity extends AppCompatActivity {

    // On définit un temps de chargement d'une durée de 3 secondes
    public static int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
    }
}