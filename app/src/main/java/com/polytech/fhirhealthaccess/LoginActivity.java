package com.polytech.fhirhealthaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.orm.SugarContext;
import com.polytech.fhirhealthaccess.database.User;

import java.util.List;

/**
 * Première interface qui s'affiche lorsque l'application est lancée.
 * Cette activité permet aux utilisateurs de l'application de s'authentifier.
 *
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SugarContext.init(this);

        editTextEmail = findViewById(R.id.editTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    /**
     * Cette méthode s'exécute lorsqu'un utilisateur clique sur le bouton "Connexion".
     * Elle extrait les valeurs renseignées par l'utilisateur (i.e adresse mail et mot de passe)
     * et vérifie que celui-ci existe dans la base de données des utilisateurs de l'application.
     * @param view Vue de l'interface sur laquelle l'utilisateur a cliqué.
     */
    public void login(View view){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        /* Afin de faciliter les tests de notre application, nous avons choisis d'utiliser une base
        de données local pour stocker les login/mots de passe des utilisateurs de l'application.
        Celle-ci devra être remplacée par une base de données externe lors de la mise en prod
        afin d'éviter d'écrire en clair dans le code les identifiants et mots de passe.*/
        User.deleteAll(User.class);
        String hashed_password = Password.hashPassword("admin");
        User u1 = new User("admin@fhir.org",hashed_password);
        u1.save();

        // Vérification des champs de connexions renseignés par l'utilisateur
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Vous devez renseigner votre adresse email et mot de passe",Toast.LENGTH_LONG).show();
        }
        else{
            List<User> users = User.listAll(User.class);
            for (User user : users){
                if(user.getEmail().equals(email) && Password.checkPassword(password,user.getPassword())){
                    Intent intent = new Intent(LoginActivity.this, ListPatientActivity.class);
                    startActivity(intent);
                    break;
                }
                else{
                    Toast.makeText(this, "Adresse email et/ou mot de passe incorrect",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}