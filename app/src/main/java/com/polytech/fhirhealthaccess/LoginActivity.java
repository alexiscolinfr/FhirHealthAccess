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

    public void login(View view){
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        User.deleteAll(User.class);
        String hashed_password = Password.hashPassword("1234");
        User u1 = new User("alexis@colin.fr",hashed_password);
        u1.save();

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Vous devez renseigner un login/mot de passe",Toast.LENGTH_LONG).show();
        }
        else{
            List<User> users = User.listAll(User.class);
            for (User user : users){
                if(user.getEmail().equals(email) && Password.checkPassword(password,user.getPassword())){
                    Intent intent = new Intent(LoginActivity.this, ListPatientActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
    }
}